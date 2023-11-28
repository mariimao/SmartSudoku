package use_case.play_game;

import entity.user.User;

public interface PlayGameDataAccessInterface {
    User get(String username); // TODO: make UserDAO implment this

}

