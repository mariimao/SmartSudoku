import entity.user.CommonUser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserTest {
    
    private CommonUser user;

    public LocalTime current_time;

    @Before
    public void init() {
        Integer score = 0;
        Map<LocalTime,Integer> scores = new HashMap<>();
        current_time = LocalTime.now();
        scores.put(current_time, score);
        user = new CommonUser(
                "Paul", "password", scores);

    }
    @Test
    public void testGetName() {
        assertEquals("Paul", user.getName());
    }
    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetScores() {
        Map<LocalTime,Integer> scores = new HashMap<>();
        scores.put(current_time, 0);
        assertEquals(scores, user.getScores());
    }
    @Test
    public void testAddScores() {
        Map<LocalTime,Integer> scores = new HashMap<>();
        scores.put(current_time, 0);
        LocalTime new_time = LocalTime.now();
        scores.put(new_time, 1);
        user.addScores(new_time, 1);
        assertEquals(scores, user.getScores());

    }


}