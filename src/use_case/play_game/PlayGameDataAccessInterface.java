package use_case.play_game;

import entity.user.User;

/**
 * Interface for the data access object of play game use cases. Implemented by UserDAO.
 */
public interface PlayGameDataAccessInterface {

    /**
     * Get the user from the database.
     *
     * @param username the username of the user
     */
    User get(String username);

}

