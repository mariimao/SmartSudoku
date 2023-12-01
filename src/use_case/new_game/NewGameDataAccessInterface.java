package use_case.new_game;

import entity.user.User;

public interface NewGameDataAccessInterface {
    boolean setProgress(User user);
    User get(String username);

}

