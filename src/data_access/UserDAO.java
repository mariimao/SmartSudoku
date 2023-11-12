package data_access;
import java.time.LocalTime;
import java.util.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.MongoCollection;
import entity.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import use_case.pause_game.PauseGameDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO implements PauseGameDataAccessInterface {
    public static void main(String[] args) {
        // Just for testing this file

        // made sample scores, boards, and users
        Map<LocalTime, Integer> scores1 = new HashMap<>();
        scores1.put(LocalTime.now(), 4);
        scores1.put(LocalTime.of(12, 30, 1), 3);
        Map<LocalTime, Integer> scores2 = new HashMap<>();
        scores2.put(LocalTime.now(), 2);
        scores2.put(LocalTime.of(12, 30, 1), 1);
        EasyBoard easyBoard = new EasyBoard();
        HardBoard hardBoard = new HardBoard();
        CommonUser u1 = new CommonUser("u1", "p1", scores1);
        CommonUser u2 = new CommonUser("u2", "p2", scores2);

        // adding the users to a DAO
        UserDAO userDAO;
        try {
            userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDAO.addUser(u1);
        userDAO.addUser(u2);

        userDAO.accounts.forEach((key, value) -> {
            System.out.println(key + ": " + value.getPassword() + ',' + value.getScores());
        });



    }
    private final MongoCollection<Document> userCollection;
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    public UserDAO(String uri, String database, String collection, UserFactory userFactory) throws Exception{
        this.userFactory = userFactory;

        //Create a MongoDB Client -> Database -> Collection (where the users are)
        //TODO: add a try catch statement
        // exception: https://mongodb.github.io/mongo-csharp-driver/2.6/apidocs/html/T_MongoDB_Driver_MongoException.htm
        this.userCollection = MongoClients.create(uri)
                .getDatabase(database)
                .getCollection(collection);

        // gets info from mongo and creates account object
        // creates list of accounts (in document form)
        List<Document> doc = userCollection.find().into(new ArrayList<>());
        for (Document account : doc) {
            String name = account.getString("name");
            if (!accounts.containsKey(name)) {
                String password = account.getString("password");
                Map<String, Integer> stringScores = account.get("scores", Map.class);

                // convert to localtime
                Map<LocalTime, Integer> scores = new HashMap<>();
                for (String time : stringScores.keySet()) {
                    scores.put(LocalTime.parse(time), scores.get(time));
                }

                User user = userFactory.create(name, password, scores);
                accounts.put(name, user);
            }
        }
    }

    public void addUser(User user){
        accounts.put(user.getName(), user);
        this.addUser();
    }

    private void addUser() { //does NOT add it name is already in database
        for (User user : accounts.values()) {
            String name = user.getName();
            String password = user.getPassword();
            Map<LocalTime, Integer> scores = user.getScores();

            // cannot store LocalTime, so must convert it to String
            Map<String, Integer> stringScores = new HashMap<>();
            for (LocalTime time : scores.keySet()) {
                stringScores.put(time.toString(), scores.get(time));
            }

            if (userCollection.countDocuments(eq("name", name)) == 0) { // not in database
                Document entry = new Document()
                        .append("_id", new ObjectId())
                        .append("name", name)
                        .append("password", password)
                        .append("scores", stringScores);
                this.userCollection.insertOne(entry);
            }
        }
    }

    public void delete(String username){
        this.userCollection.deleteOne(eq("username", username));
        // below is alternative method that returns info of the deleted user
        //Document user = this.userCollection.findOneAndDelete(eq("username", username));
        accounts.remove(username);
    }

    public void deleteAll(){
        this.userCollection.deleteMany(new Document());
        accounts.clear();
    }

    public void addScore(User user, LocalTime time, Integer score){
        accounts.get(user.getName()).addScores(time, score);
        this.changeScores(user);
    }

    private void changeScores(User user) {
        this.userCollection.findOneAndUpdate(
                eq("name", user.getName()), //find by name
                eq("scores", user.getScores())); //update score
    }

    public String toString(){
        return accounts.toString();
    }

    @Override
    public void saveProgress() {
        // TODO: implement for the PauseGame use case. It should save the user's progress somewhere in their account

    }
}

/*
  Temporary
  GUIDES/DOCUMENTATION:
  https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html
  https://www.mongodb.com/products/tools/compass
  https://www.mongodb.com/developer/languages/java/java-setup-crud-operations/#delete-documents
  https://www.baeldung.com/java-mongodb
  https://hevodata.com/learn/mongodb-java/#Step_10_Query_Documents

  TODO: SLFJ4 logger warning:
  https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/logging/
  https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder
  https://www.slf4j.org/codes.html#StaticLoggerBinder
  https://stackoverflow.com/questions/23775906/maven-dependency-and-logging-slf4j-and-log4j
 */