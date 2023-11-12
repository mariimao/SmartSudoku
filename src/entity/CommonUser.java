package entity;

import java.time.LocalTime;
import java.util.Map;

public class CommonUser implements User{

    private final String name;
    private final String password;
    private final Map<LocalTime, Integer> scores;
    private GameState pausedGame;

    public CommonUser(String name, String password, Map<LocalTime, Integer> scores){
        this.name = name;
        this.password = password;
        this.scores = scores;
        this.pausedGame = null;
    }

    public String getName() { return name;}

    public String getPassword() { return password; }

    public Map<LocalTime, Integer> getScores() {
        return scores;
    }

    public void addScores(LocalTime time, Integer score) {
        scores.put(time, score);
    }

    @Override
    public void addPausedGame(GameState currentGame) {this.pausedGame = currentGame;}

    @Override
    public GameState getPausedGame() {return this.pausedGame;}
}
