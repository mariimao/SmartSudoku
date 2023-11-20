package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

public interface ResumeGameDataAccessInterface {
    GameState getProgress(User user);

    User get(String username);
}
