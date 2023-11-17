package use_case.pause_game;

import entity.User;

public interface PauseGameDataAccessInterface {
    boolean saveProgress(User user);
}
