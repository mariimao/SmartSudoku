package use_case.end_game;

import entity.user.User;

public interface EndGameDataAccessInterface {
    User get(String username);
}
