package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class CommonUser implements User{

    private final String name;
    private final String password;

    private final Map<LocalTime, Integer> scores;

    public CommonUser(String name, String password, Map<LocalTime, Integer> scores){
        this.name = name;
        this.password = password;
        this.scores = scores;
    }

    public String getName() { return name;}

    public String getPassword() { return password; }

    public Map<LocalTime, Integer> getScores() {
        return scores;
    }

    public void addScores(LocalTime time, Integer score) {
        scores.put(time, score);
    }
}
