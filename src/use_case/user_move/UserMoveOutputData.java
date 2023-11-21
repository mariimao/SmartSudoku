package use_case.user_move;

import entity.GameState;

public class UserMoveOutputData {
    private final GameState gameState;
    private boolean useCaseFailed;

    public UserMoveOutputData (GameState gameState, boolean useCaseFailed) {
        this.gameState = gameState;
        this.useCaseFailed = useCaseFailed;

    }

    public GameState getGameState() {return gameState;}
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
