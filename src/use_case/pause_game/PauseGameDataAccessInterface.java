package use_case.pause_game;

import entity.User;

public interface PauseGameDataAccessInterface {
    void saveProgress(User user);
}
