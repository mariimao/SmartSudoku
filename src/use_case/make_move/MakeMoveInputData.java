package use_case.make_move;

import entity.board.Board;
import entity.board.GameState;

public class MakeMoveInputData {
    final private String userName;
    final private int moveValue;
    final private int moveRow;
    final private int moveCol;
    final private GameState gameBeingPlayed;


    public MakeMoveInputData(String userName, int moveValue, int moveRow, int moveCol, GameState gameBeingPlayed) {
        this.userName = userName;
        this.moveValue = moveValue;
        this.moveRow = moveRow;
        this.moveCol = moveCol;
        this.gameBeingPlayed = gameBeingPlayed;
    }

    int getMoveValue() {return moveValue;}
    int getMoveRow() {return moveRow;}
    int getMoveCol() {return moveCol;}
    Board scrambleBoard() {return gameBeingPlayed.scrambleBoard();}

    GameState getGameBeingPlayed() {return gameBeingPlayed;}

    public String getUserName() {
        return userName;
    }

}
