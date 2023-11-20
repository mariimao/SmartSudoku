package use_case.pause_game;

import entity.board.GameState;
import entity.user.User;

public class PauseGameOutputData {
    // TODO: how will we conserve time when pausing?
    private final User user;
    private boolean useCaseFailed;

    public PauseGameOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public GameState getCurrentGame() {return user.getPausedGame();}

    public boolean isUseCaseFailed() {return useCaseFailed;}
}
