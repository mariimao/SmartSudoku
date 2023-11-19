package use_case.pause_game;

import entity.user.User;

public interface PauseGameDataAccessInterface {
    boolean saveProgress(User user);
}
