package use_case.signup;

import entity.user.User;

/**
 * Interface for the data access object of Signup use cases. Implemented by UserDAO.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Adds a user to the database.
     * @param user the user to add to the database
     */
    void addUser(User user);

    /**
     * Checks if a username already exists in the database
     * @param username username to check in database
     */
    boolean existsByName(String username);

    /**
     * Deletes a user from the database
     * @param name username to refer to delete user
     */
    void delete(String name);
}
