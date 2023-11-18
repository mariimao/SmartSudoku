package data_access;
import java.time.LocalTime;
import java.util.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import entity.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import use_case.menu.MenuUserDataAccessInterface;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.start.StartUserDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class UserDAO implements PauseGameDataAccessInterface, StartUserDataAccessInterface, MenuUserDataAccessInterface {
    public static void main(String[] args) {
        // TODO: DELETE MAIN, Just for testing this file

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
            System.out.println("Username: " + key + "Password: " + value.getPassword() + "Scores: " + value.getScores() + "Paused Game: " + value.getPausedGame());
        });

        u1.setPausedGame(new GameState(1));
        userDAO.accounts.forEach((key, value) -> {
            System.out.println("Username: " + key + "  Password: " + value.getPassword() + "  Scores: " + value.getScores() + "  Paused Game: " + value.getPausedGame());
        });
        userDAO.saveProgress(u1);
        System.out.println(userDAO.toString());
    }

    private final MongoCollection<Document> userCollection;
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    public UserDAO(String uri, String database, String collection, UserFactory userFactory) throws Exception {
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
//                Map<String, Integer> stringScores = account.get("scores", Map.class);   // TODO: remove this comment. I only commented it out because of the NUllPointer Error
//
//                // convert to localtime
//                Map<LocalTime, Integer> scores = new HashMap<>();
//                for (String time : stringScores.keySet()) {
//                    scores.put(LocalTime.parse(time), scores.get(time));
//                }
                // User user = userFactory.create(name, password, scores);
                // accounts.put(name, user);
            }
        }
    }

    public void addUser(User user) {
        accounts.put(user.getName(), user);
        this.addUser();
    }

    private void addUser() { //does NOT add it name is already in database
        for (User user : accounts.values()) {
            String name = user.getName();
            String password = user.getPassword();
            Map<LocalTime, Integer> scores = user.getScores();
            GameState pausedGame = user.getPausedGame();

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
                        .append("scores", stringScores)
                        .append("pausedGamePastBoards", null)
                        .append("pausedgame", null);
                this.userCollection.insertOne(entry);
            }
        }
    }

    public boolean existsByName(String name) {
        return accounts.containsKey(name);
    }

    public void delete(String name) {
        this.userCollection.deleteOne(eq("name", name));
        // below is alternative method that returns info of the deleted user
        //Document user = this.userCollection.findOneAndDelete(eq("username", username));
        accounts.remove(name);
    }

    public void deleteAll() {
        this.userCollection.deleteMany(new Document());
        accounts.clear();
    }

    public void addScore(User user, LocalTime time, Integer score) {
        accounts.get(user.getName()).addScores(time, score);
        this.changeScores(user);
    }

    private void changeScores(User user) {
        this.userCollection.findOneAndUpdate(
                eq("name", user.getName()), //find by name
                eq("scores", user.getScores())); //update score
    }

    public String toString() {
        return accounts.toString();
    }

    @Override
    public boolean saveProgress(User user) {
        // TODO: if Board implementation changes form HashMap[] to int[][] switch from string rep to array rep
        // ASSUMPTION: this method would only ever be called if the User.pausedGame is not null
        // returns true if game was paused successfully and false otherwise
        String name = user.getName();
        GameState pausedGame = user.getPausedGame();

        Bson filter = Filters.eq("name", name);   // Creating a filter
        Bson updateGameState = Updates.set("pausedgame", pausedGame.toStringPause());    // Creating an update for the game state
        UpdateResult resultGameState = this.userCollection.updateOne(filter, updateGameState);   // Performing the update for the game state

        Bson updatePastGames = Updates.set("pastgames", pausedGame.getPastStates());   // Creating an update for the past games
        UpdateResult resultPastGames = this.userCollection.updateOne(filter, updatePastGames);   // Performing the update for the past games

        // Check if the document was found and updated for the game state
        boolean gamePaused = false;
        if (resultGameState.getMatchedCount() == 1) {
            gamePaused = true;
        }

        // Check if the document was found and updated for the past games
        if (resultPastGames.getMatchedCount() == 1) {
            gamePaused = true;
        }
        return gamePaused;
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
      akunna: I think I fixed this issue
  https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/logging/
  https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder
  https://www.slf4j.org/codes.html#StaticLoggerBinder
  https://stackoverflow.com/questions/23775906/maven-dependency-and-logging-slf4j-and-log4j
 */