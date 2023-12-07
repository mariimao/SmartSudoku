package entity.user;

import java.time.LocalTime;
import java.util.Map;

/**
 * Class represents a Common user factory in the game. Creates a common user.
 */
public class CommonUserFactory implements UserFactory {

    /**
     * Creates a new CommonUser object.
     *
     * @param name     is a String object representing the username
     * @param password is a String object representing the password
     * @param scores   is a Map object of the scores, which are represented in < Time, Score > format
     * @return a new CommonUser object
     */
    @Override
    public User create(String name, String password, Map<LocalTime, Integer> scores) {
        return new CommonUser(name, password, scores);
    }
}
