package entity.user;

import java.time.LocalTime;
import java.util.Map;

/**
 * Class represents a Common user factory in the game. Creates a common user.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, Map<LocalTime, Integer> scores){
        return new CommonUser(name, password, scores);
    }
}
