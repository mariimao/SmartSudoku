package data_access;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import entity.board.GameState;
import entity.user.User;
import entity.user.UserFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.make_move.MakeMoveDataAccessInterface;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.start.StartUserDataAccessInterface;

import java.time.LocalTime;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

/**
 * The user data access object. Retrieves data from MongoDB and creates map containing information about the users.
 */
public class UserDAO implements PauseGameDataAccessInterface, StartUserDataAccessInterface, ResumeGameDataAccessInterface,
        SignupUserDataAccessInterface, LoginUserDataAccessInterface, MenuUserDataAccessInterface,
        NewGameDataAccessInterface, LeaderboardDataAccessInterface,
        EndGameDataAccessInterface, PlayGameDataAccessInterface, MakeMoveDataAccessInterface {

    private final MongoCollection<Document> userCollection;
    private final Map<String, User> accounts = new HashMap<>(); // a map of usernames to the associated User object
    private final UserFactory userFactory;

    /**
     * Initializes the UserDAO.
     *
     * @param uri         the uri of the mongoclient
     * @param database    the name of the database
     * @param collection  the collection name
     * @param userFactory is a UserFactory object
     */
    public UserDAO(String uri, String database, String collection, UserFactory userFactory) throws MongoException {
        this.userFactory = userFactory;

        //Create a MongoDB Client -> Database -> Collection (where the users are)
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
                    List<String> pausedGamePastBoards = (List<String>) account.get("pausedGamePastBoards");
                    // ignore the warning userDOc.get("pausedGamePastBoards" will always be able tp cast)
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
                    scores.put(LocalTime.now(), 0);
                } else {
                    for (String time : stringScores.keySet()) {
                        scores.put(LocalTime.parse(time), stringScores.get(time));
                    }
                }

                User user = userFactory.create(name, password, scores);
                user.setPausedGame(gameState);
                accounts.put(name, user);
            }
        }
    }


    /**
     * adds a new user
     *
     * @param user is a User type
     */
    public void addUser(User user) {
        accounts.put(user.getName(), user);
        this.addUser();
    }

    /**
     * adds users to the MongoDB database based on the accounts map.
     * Does not add if the name of a user is in the database
     */
    private void addUser() {
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

    /**
     * Checks if the name appears in the accounts map
     *
     * @param name the name we want to check
     * @return true if it does appear
     */
    public boolean existsByName(String name) {
        return accounts.containsKey(name);
    }

    /**
     * returns the user based off the name
     *
     * @param username the username we want to look for
     * @return the user
     */
    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    /**
     * @return the accounts map
     */
    public Map<String, User> getAccounts() {
        return accounts;
    }

    /**
     * deletes the user from accounts
     *
     * @param name the username we want to delete
     */
    public void delete(String name) {
        this.userCollection.deleteOne(eq("name", name));
        // below is alternative method that returns info of the deleted user
        //Document user = this.userCollection.findOneAndDelete(eq("username", username));
        accounts.remove(name);
    }

    /**
     * deletes all user from database and accounts map
     */
    public void deleteAll() {
        this.userCollection.deleteMany(new Document());
        accounts.clear();
    }

    /**
     * adds a score to the user
     *
     * @param user  the user who achieved the score
     * @param time  the time they completed it
     * @param score the score they achieved
     */
    public void addScore(User user, LocalTime time, Integer score) {
        accounts.get(user.getName()).addScores(time, score);
        this.changeScores(user);
    }

    /**
     * updates the user in the database to include their new score
     *
     * @param user is a User object
     */
    private void changeScores(User user) {
        Map<String, Integer> stringScores = new HashMap<>();
        for (LocalTime time : user.getScores().keySet()) {
            stringScores.put(time.toString(), user.getScores().get(time));
        }

        this.userCollection.deleteOne(eq("name", user.getName()));
        Document entry = new Document()
                .append("_id", new ObjectId())
                .append("name", user.getName())
                .append("password", user.getPassword())
                .append("scores", stringScores)
                .append("pausedGamePastBoards", user.getPausedGame())
                .append("pausedgame", null);
        this.userCollection.insertOne(entry);
    }

    /**
     * string representation of accounts
     *
     * @return a string
     */
    public String toString() {
        return accounts.toString();
    }

    /**
     * sets the final game
     *
     * @param user is a User object
     * @return a boolean value, representing whether the function has been implemented properly
     */
    public boolean setFinalGame(User user) {
        String name = user.getName();
        GameState finalGame = user.getFinalGame();

        Bson filter = Filters.eq("name", name);   // Creating a filter
        Bson updateGameState = Updates.set("finalgame", finalGame.toStringPause());    // Creating an update for the game state
        UpdateResult resultGameState = this.userCollection.updateOne(filter, updateGameState);   // Performing the update for the game state

        List<String> finalGamePastStates = new ArrayList<>();
        for (GameState state : finalGame.getPastStates()) {
            String value = state.getCurrBoard().toStringPause();
            finalGamePastStates.add(value);
        }
        Bson updateFinalGames = Updates.set("finalGamePastBoards", finalGamePastStates);   // Creating an update for the past games
        UpdateResult resultFinalGames = this.userCollection.updateOne(filter, updateFinalGames);   // Performing the update for the past games
        accounts.put(name, user);

        return accounts.get(user.getName()).getFinalGame() == finalGame;
    }

    /**
     * Sets the progress of the user.
     * ASSUMPTION: this method would only ever be called if the User.pausedGame is not null
     *
     * @param user is a User object
     * @return true if game was paused successfully and false otherwise
     */
    public boolean setProgress(User user) {
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

    /**
     * gets the progress of the user
     *
     * @param userName name of the user
     * @return gameState
     */
    public GameState getProgress(String userName) {
        // return the game state in account that corresponds to this
        if (accounts.containsKey(userName)) {
            return accounts.get(userName).getPausedGame();
        } else {
            // return null if the user does not exist
            return null;
        }
    }

}