package entity.user;

import entity.user.User;

import java.time.LocalTime;
import java.util.Map;

/**
 * Interface for the UserFactory
 * Represents a user factory. Current classes that implement this are Common user factory
 */
public interface UserFactory {

    User create(String name, String password, Map<LocalTime, Integer> scores);
}
