package entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public interface User {

    String getName();

    String getPassword();

    Map<LocalTime, Integer> getScores();

    void addScores(LocalTime time, Integer score);

    void addPausedGame(GameState currentGame);

    GameState getPausedGame();

}
