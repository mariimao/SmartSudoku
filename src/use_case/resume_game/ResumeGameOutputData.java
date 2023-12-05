package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

public class ResumeGameOutputData {
    // TODO: how will we conserve time when pausing?
    private final User user;
    private boolean useCaseFailed;

    public ResumeGameOutputData(User user, boolean useCaseFailed) {

        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public GameState getPausedGame() {return user.getPausedGame();}
}
