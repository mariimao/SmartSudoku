package use_case.make_move;

import entity.board.GameState;

/**
 * Class representing the input data of the MakeMove use case.
 */
public class MakeMoveInputData {
    final private int moveValue;
    final private int moveRow;
    final private int moveCol;
    final private GameState gameBeingPlayed;

    /**
     * Constructor for the MakeMove input data object.
     *
     * @param moveValue       is the int value of the user's move
     * @param moveRow         is the int value of the row from the user's move
     * @param moveCol         is the int value of the column from the user's move
     * @param gameBeingPlayed is a GameState of the game being played
     */
    public MakeMoveInputData(int moveValue, int moveRow, int moveCol, GameState gameBeingPlayed) {
        this.moveValue = moveValue;
        this.moveRow = moveRow;
        this.moveCol = moveCol;
        this.gameBeingPlayed = gameBeingPlayed;
    }

    /**
     * When the user makes an incorrect move, a life is deducted from the GameState.
     */
    public void loseLife() {
        gameBeingPlayed.loseLife();
    }

    /* ----- Getters and setters ----- */
    int getMoveValue() {
        return moveValue;
    }

    int getMoveRow() {
        return moveRow;
    }

    int getMoveCol() {
        return moveCol;
    }

    public GameState getGameBeingPlayed() {
        return gameBeingPlayed;
    }
}
