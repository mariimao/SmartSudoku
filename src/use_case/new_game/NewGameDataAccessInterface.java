package use_case.new_game;

import entity.user.User;

public interface NewGameDataAccessInterface { // TODO: make UserDAO implment this
    boolean setProgress(User user);
    User get(String username);

}

