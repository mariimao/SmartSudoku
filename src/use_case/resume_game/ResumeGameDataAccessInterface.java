package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

public interface ResumeGameDataAccessInterface {
    User get(String username);

    GameState getProgress(String username);
}
