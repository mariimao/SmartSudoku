package entity.user;

import entity.board.GameState;

import java.time.LocalTime;
import java.util.Map;

/**
 * Class represents a Common user (player) in the game.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final Map<LocalTime, Integer> scores;
    private GameState pausedGame;
    private GameState finalGame;


    /**
     * Constructer for Common User
     *
     * @param name     represents the username set by the player
     * @param password represents the password set by the player
     * @param scores   represents a mapping of the previous scores from previous games
     */
    public CommonUser(String name, String password, Map<LocalTime, Integer> scores) {
        this.name = name;
        this.password = password;
        this.scores = scores;
        this.pausedGame = null;
        this.finalGame = null;
    }

    /**
     * Returns the username of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the password of the user to verify
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the scores of past games
     */
    public Map<LocalTime, Integer> getScores() {
        return scores;
    }

    /**
     * Updates the scores of the users by adding a new game and time.
     */
    public void addScores(LocalTime time, Integer score) {
        scores.put(time, score);
    }

    /**
     * Gets the most recent game played
     */
    public GameState getFinalGame() {
        return this.finalGame;
    }

    /**
     * Sets the state of the game to be the final or most recent game played
     */
    @Override
    public void setFinalGame(GameState currentGame) {
        this.finalGame = currentGame;
    }

    /**
     * Checks if the game is currently paused. Will get null pointer if game is not paused.
     */
    @Override
    public GameState getPausedGame() {
        return this.pausedGame;
    }  // you will get a null pointer exception if this returns null and a method is applied to it

    /**
     * Sets the state of the game to be paused
     */
    @Override
    public void setPausedGame(GameState currentGame) {
        this.pausedGame = currentGame;
    }
}
