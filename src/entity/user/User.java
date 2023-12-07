package entity.user;

import entity.board.GameState;

import java.time.LocalTime;
import java.util.Map;

/**
 * Interface for the User
 * Represents a User in the database. Current classes that implement this are
 * User
 */
public interface User {

    /**
     * Returns the username of the user
     */
    String getName();

    /**
     * Returns the password of the user to verify
     */
    String getPassword();

    /**
     * Returns the scores of past games
     */
    Map<LocalTime, Integer> getScores();

    /**
     * Updates the scores of the users by adding a new game and time.
     */
    void addScores(LocalTime time, Integer score);

    /**
     * Gets the most recent game played
     */
    GameState getFinalGame();

    /**
     * Sets the state of the game to be the final or most recent game played
     */
    void setFinalGame(GameState currentGame);

    /**
     * Checks if the game is currently paused. Will get null pointer if game is not paused.
     */
    GameState getPausedGame();

    /**
     * Sets the state of the game to be paused
     */
    void setPausedGame(GameState currentGame);

}
