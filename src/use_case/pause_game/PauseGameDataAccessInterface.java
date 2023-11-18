package use_case.pause_game;

import entity.user.User;

public interface PauseGameDataAccessInterface {
    void saveProgress(User user);
}
