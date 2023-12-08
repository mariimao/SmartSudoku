package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Class representing the output data for resume game.
 */

public class ResumeGameOutputData {
    private final User user;
    private final boolean useCaseFailed;

    /**
     * Constructor for an ResumeGameOutputData object.
     *
     * @param user     is the user.
     * @param useCaseFailed is a boolean that determines if the use case was successful
     */

    public ResumeGameOutputData(User user, boolean useCaseFailed) {

        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * @return the paused game state
     */

    public GameState getPausedGame() {
        return user.getPausedGame();
    }
}
