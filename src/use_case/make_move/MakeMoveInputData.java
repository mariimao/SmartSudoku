package use_case.make_move;

import entity.board.GameState;

public class MakeMoveInputData {
    final private int moveValue;
    final private int moveRow;
    final private int moveCol;
    final private GameState gameBeingPlayed;


    public MakeMoveInputData(int moveValue, int moveRow, int moveCol, GameState gameBeingPlayed) {
        this.moveValue = moveValue;
        this.moveRow = moveRow;
        this.moveCol = moveCol;
        this.gameBeingPlayed = gameBeingPlayed;
    }

    int getMoveValue() {return moveValue;}
    int getMoveRow() {return moveRow;}
    int getMoveCol() {return moveCol;}
    GameState getGameBeingPlayed() {return gameBeingPlayed;}

}
