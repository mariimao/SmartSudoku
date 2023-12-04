package entity.user;

import entity.user.User;

import java.time.LocalTime;
import java.util.Map;

/**
 * Interface for the UserFactory
 * Represents a user factory. Current classes that implement this are Common user factory
 */
public interface UserFactory {

    /**
     * Creates a new User object.
     * @param name is a String object representing the username
     * @param password is a String object representing the password
     * @param scores is a Map object of the scores, which are represented in < Time, Score > format
     * @return a new User object
     */
    User create(String name, String password, Map<LocalTime, Integer> scores);
}
