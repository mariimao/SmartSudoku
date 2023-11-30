package data_access;

import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import entity.board.Board;
import entity.user.*;
import entity.board.EasyBoard;
import entity.board.GameState;
import entity.board.HardBoard;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import use_case.end_game.EndGameDataAccessInterface;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.start.StartUserDataAccessInterface;
import use_case.user_move.UserMoveDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class UserDAO implements PauseGameDataAccessInterface, StartUserDataAccessInterface, ResumeGameDataAccessInterface,
                                SignupUserDataAccessInterface, LoginUserDataAccessInterface, MenuUserDataAccessInterface,
                                NewGameDataAccessInterface, LeaderboardDataAccessInterface, UserMoveDataAccessInterface,
                                EndGameDataAccessInterface, PlayGameDataAccessInterface {
    public static void main(String[] args) {

        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF); //FOR LOGGER

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
                Map<String, Integer> stringScores = account.get("scores", Map.class);

                // retrieving their game from mongo and setting it into their user in accounts
                GameState gameState = null;
                String gameAsString = account.getString("pausedgame");
                if (gameAsString != null && gameAsString.contains("-")) {
                    ArrayList<String> lastPauseGamePastStates = (ArrayList<String>) account.get("pausedGamePastBoards");


                    String[] gameAsArray = gameAsString.split("-");
                    String gameValues = gameAsArray[0];
                    int difficulty = Integer.parseInt(gameAsArray[1]);
                    int lives = Integer.parseInt(gameAsArray[2]);

                    // creates a LinkedList of past game states from data stored in mongoDB
                    List<String> pausedGamePastBoards = (List<String>) account.get("pausedGamePastBoards");  // ignore the warning userDOc.get("pausedGamePastBoards" will always be able tp cast)
                    LinkedList<GameState> pauseGameStates = new LinkedList<>();
                    for (String values : pausedGamePastBoards) {
                        GameState tempState = new GameState(difficulty);
                        tempState.setCurrBoard(values);
                        pauseGameStates.add(tempState);
                    }

                    // setting all the data into the given instance of user based on the data collected
                    gameState = new GameState(difficulty, pauseGameStates);
                    gameState.setCurrBoard(gameValues);
                    gameState.setLives(lives);

                }


                // convert to localtime
                Map<LocalTime, Integer> scores = new HashMap<>();


                if (stringScores == null) {
                    scores.put(LocalTime.now(), 0); //TODO: may need to change
                } else {
                    for (String time : stringScores.keySet()) {
                        scores.put(LocalTime.parse(time), scores.get(time));
                    }
                }

                User user = userFactory.create(name, password, scores);
                user.setPausedGame(gameState);
                accounts.put(name, user);
            }
        }
    }

    @Override
    public boolean existsbyName(String username) {
        return false;
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

            String pausedGameStr = "yes";
            if (pausedGame != null) {
                pausedGameStr = pausedGame.toStringPause();
            }

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
                        .append("pausedGamePastBoards", new ArrayList<String>())
                        .append("pausedgame", null);
                this.userCollection.insertOne(entry);
            }
        }
    }

    public boolean existsByName(String name) {
        return accounts.containsKey(name);
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    public Map<String, User> getAccounts() {
        return accounts;
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
    public boolean setProgress(User user) {
        // ASSUMPTION: this method would only ever be called if the User.pausedGame is not null
        // returns true if game was paused successfully and false otherwise
        String name = user.getName();
        GameState pausedGame = user.getPausedGame();

        Bson filter = Filters.eq("name", name);   // Creating a filter
        Bson updateGameState = Updates.set("pausedgame", pausedGame.toStringPause());    // Creating an update for the game state
        UpdateResult resultGameState = this.userCollection.updateOne(filter, updateGameState);   // Performing the update for the game state

        List<String> pausedGamePastStates = new ArrayList<>();
        for (GameState state : pausedGame.getPastStates()) {
            String value = state.getCurrBoard().toStringPause();
            pausedGamePastStates.add(value);
        }
        Bson updatePastGames = Updates.set("pausedGamePastBoards", pausedGamePastStates);   // Creating an update for the past games
        UpdateResult resultPastGames = this.userCollection.updateOne(filter, updatePastGames);   // Performing the update for the past games
        accounts.put(name, user);

        return accounts.get(user.getName()).getPausedGame() == pausedGame;
    }

    public Document getUserDocByName(User user) {
        return userCollection.find(eq("name", user.getName())).first();
    }


    public GameState getProgress(User user) {
        // retrieves user's data from database then sets user.PauseGame to whatever it finds
        Document userDoc = getUserDocByName(user);
        if (userDoc != null) {
            String gameAsString = userDoc.getString("pausedgame");

            if (gameAsString == null) {
                return null;
            }  // returns null if there is no game
            else {  // create a new game state based on the data stored in mongo
                String[] gameAsArray = gameAsString.split("-");
                String gameValues = gameAsArray[0];
                int difficulty = Integer.parseInt(gameAsArray[1]);
                int lives = Integer.parseInt(gameAsArray[2]);

                // creates a LinkedList of past game states from data stored in mongoDB
                List<String> pausedGamePastBoards = (List<String>) userDoc.get("pausedGamePastBoards");  // ignore the warning userDOc.get("pausedGamePastBoards" will always be able tp cast)
                LinkedList<GameState> pauseGameStates = new LinkedList<>();
                for (String values : pausedGamePastBoards) {
                    GameState tempState = new GameState(difficulty);
                    tempState.setCurrBoard(values);
                    pauseGameStates.add(tempState);
                }

                // setting all the data into the given instance of user based on the data collected
                GameState gameState = new GameState(difficulty, pauseGameStates);
                gameState.setCurrBoard(gameValues);
                gameState.setLives(lives);
                user.setPausedGame(gameState);
                accounts.put(user.getName(), user);

                return gameState;
            }
        }
        throw new NoSuchElementException();
    }
    public GameState getProgress(String userName) {
        // return the game state in account that corresponds to this
        if (accounts.containsKey(userName)) {
            return accounts.get(userName).getPausedGame();
        } else {
            // return null if the user does not exist
            return null;
        }
    }

    @Override
    public GameState saveBoard(GameState gameState) {
        return null;
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

  https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/logging/
  https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder
  https://www.slf4j.org/codes.html#StaticLoggerBinder
  https://stackoverflow.com/questions/23775906/maven-dependency-and-logging-slf4j-and-log4j
 */