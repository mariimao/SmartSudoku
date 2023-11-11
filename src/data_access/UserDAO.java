package data_access;
import java.util.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import entity.User;
import entity.UserFactory;

import com.mongodb.MongoException;
import use_case.pause_game.PauseGameDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO implements PauseGameDataAccessInterface {
    private final MongoCollection<Document> userCollection;
    private final Map<String, String> accounts = new HashMap<>(); // TODO: change to User class
    private UserFactory userFactory;

    //TODO: new attribute of each user with list of scores/times
    public UserDAO(String uri, String database, String collection, UserFactory userFactory) throws Exception{
        this.userFactory = userFactory;

        //Create a MongoDB Client -> Database -> Collection (where the users are)
        //TODO: add a try catch statement
        // exception: https://mongodb.github.io/mongo-csharp-driver/2.6/apidocs/html/T_MongoDB_Driver_MongoException.htm
        this.userCollection = MongoClients.create(uri)
                .getDatabase(database)
                .getCollection(collection);

        // creates list of accounts (in document form)
        List<Document> accounts = userCollection.find().into(new ArrayList<>());
        for (Document account : accounts) {
            //TODO: check its not already in accounts
            String username = account.getString("username");
            String password = account.getString("password");
            //List scores??
            this.addUser(username, password);
        }
    }

    //TODO: add scores?
    public void addUser(String username, String password){
        if (!accounts.containsKey(username)) {
            InsertOneResult result = this.userCollection.insertOne(new Document()
                            .append("_id", new ObjectId())
                            .append("username", username)
                            .append("password", password)
                            //.append("scores", {time, score})?????
            );
            accounts.put(username, password);
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

    public void addNewScore(){
        //TODO
    }

    public void changePassword(){
        //maybe TODO?
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