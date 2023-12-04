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

    String getName();

    String getPassword();

    Map<LocalTime, Integer> getScores();

    void addScores(LocalTime time, Integer score);

    void setPausedGame(GameState currentGame);

    void setFinalGame(GameState currentGame);

    GameState getFinalGame();

    GameState getPausedGame();

}
