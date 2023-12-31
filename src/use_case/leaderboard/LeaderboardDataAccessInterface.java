package use_case.leaderboard;

import entity.user.User;

import java.util.Map;

/**
 * Interface for the data access object of a Leaderboard. Implemented by UserDAO.
 */
public interface LeaderboardDataAccessInterface {

    /**
     * Checks if the name appears in the accounts map.
     *
     * @param username is the name we want to check
     * @return true if it does appear
     */
    boolean existsByName(String username);

    /**
     * Adds a new user.
     *
     * @param user is a User type
     */
    void addUser(User user);

    /* ----- Getters and setters ----- */
    Map<String, User> getAccounts();
}
