package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Interface for the data access object of resume game use cases. Implemented by UserDAO.
 */
public interface ResumeGameDataAccessInterface {

    /**
     * Gets a user from the database.
     *
     * @param username the username to get from the database
     */
    User get(String username);

    /**
     * Gets the user's game progress from the database.
     *
     * @param username the username of the current user
     */

    GameState getProgress(String username);
}
