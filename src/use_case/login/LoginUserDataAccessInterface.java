package use_case.login;

import entity.user.User;
import interface_adapter.login.LoginState;

/**
 * Interface for the data access object of Login use cases. Implemented by UserDAO.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if a username already exists in the database
     * @param username username to check in database
     */
    boolean existsByName(String username);

    /**
     * Returns a string username
     * @param username username to check in database
     */
    User get(String username);

    /**
     * Adds a username to the database
     * @param user user to add in the database
     */
    void addUser(User user);
}
