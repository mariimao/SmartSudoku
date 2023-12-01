package use_case.make_move;

import entity.board.GameState;
import entity.user.User;

public class MakeMoveOutputData {
    private GameState gameBeingPlayed;

    public MakeMoveOutputData(GameState gameBeingPlayed) {
        this.gameBeingPlayed = gameBeingPlayed;
    }
    public GameState getGameBeingPlayed() {return gameBeingPlayed;}
}
