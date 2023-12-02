package use_case.end_game;

import entity.user.User;

import java.time.LocalTime;

public interface EndGameDataAccessInterface {
    User get(String username);

    void addScore(User user, LocalTime time, Integer score);
}
