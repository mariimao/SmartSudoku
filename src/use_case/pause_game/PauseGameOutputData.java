package use_case.pause_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Class representing the output data for PauseGame.
 */
public class PauseGameOutputData {
    private final User user;
    private final boolean useCaseFailed;

    /**
     * Constructor method for the PauseGameOutputData object.
     *
     * @param user          is a User object
     * @param useCaseFailed is a boolean object
     */
    public PauseGameOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    /* ----- Getters and setters ----- */
    public GameState getCurrentGame() {
        return user.getPausedGame();
    }

    public User getUser() {
        return user;
    }
}
