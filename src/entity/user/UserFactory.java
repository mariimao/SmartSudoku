package entity.user;

import entity.user.User;

import java.time.LocalTime;
import java.util.Map;

public interface UserFactory {

    User create(String name, String password, Map<LocalTime, Integer> scores);
}
