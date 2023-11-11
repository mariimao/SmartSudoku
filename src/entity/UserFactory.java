package entity;

import java.time.LocalTime;
import java.util.Map;

public interface UserFactory {

    User create(String name, String password, Map<LocalTime, Integer> scores);
}
