package use_case.make_move;

import entity.board.GameState;

/**
 * Class representing the output data for MakeMove use case.
 */
public class MakeMoveOutputData {
    private final GameState gameBeingPlayed;

    /**
     * Is a constructor for a MakeMoveOutputData object.
     * @param gameBeingPlayed is the state of the game being played
     */
    public MakeMoveOutputData(GameState gameBeingPlayed) {
        this.gameBeingPlayed = gameBeingPlayed;
    }

    /* ----- Getters and setters ----- */
    public GameState getGameBeingPlayed() {return gameBeingPlayed;}
}
