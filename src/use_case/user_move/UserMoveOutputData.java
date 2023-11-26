package use_case.user_move;

import entity.board.GameState;

public class UserMoveOutputData {
    private final GameState gameState;

    public UserMoveOutputData (GameState gameState) {
        this.gameState = gameState;

    }

    public GameState getGameState() {return gameState;}
}
