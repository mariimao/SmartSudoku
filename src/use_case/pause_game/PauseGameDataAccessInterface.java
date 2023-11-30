package use_case.pause_game;

import entity.user.User;

public interface PauseGameDataAccessInterface {
    boolean setProgress(User user);

    User get(String username);
}
