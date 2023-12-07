package use_case.end_game;

import entity.user.User;

import java.time.LocalTime;

/**
 * Interface for the data access object of an EndGame. Implemented by UserDAO.
 */
public interface EndGameDataAccessInterface {

    /**
     * Adds a score to the user.
     *
     * @param user  the user who achieved the score
     * @param time  the time they completed it
     * @param score the score they achieved
     */
    void addScore(User user, LocalTime time, Integer score);

    /* ----- Getters and setters ----- */
    User get(String username);

    boolean setFinalGame(User user);
}
