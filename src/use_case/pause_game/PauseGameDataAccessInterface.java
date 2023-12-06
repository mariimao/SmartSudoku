package use_case.pause_game;

import entity.user.User;

/**
 * Interface for the data access object of a PauseGame use case. Implemented by UserDAO.
 */
public interface PauseGameDataAccessInterface {

    /**
     * Sets the progress of the user.
     * ASSUMPTION: this method would only ever be called if the User.pausedGame is not null
     *
     * @param user is a User object
     * @return true if game was paused successfully and false otherwise
     */
    boolean setProgress(User user);

    /**
     * returns the user based off the name
     *
     * @param username the username we want to look for
     * @return the user
     */
    User get(String username);
}
