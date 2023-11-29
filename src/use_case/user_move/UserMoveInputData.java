package use_case.user_move;

import entity.board.Board;
import entity.board.GameState;

public class UserMoveInputData {

    final public GameState current_state;
    final private int row;
    final private int column;
    final private int value;

    public UserMoveInputData(GameState currentState, int row, int column, int value) {
        this.current_state = currentState;
        this.row = row;
        this.column = column;
        this.value = value;

}

    Board getCurrent_board() {return current_state.getCurrBoard();}

    void loseLife() {current_state.loseLife();}

    Board makeMove() {return current_state.makeMove(row, column, value);}

    boolean gameOver() {return current_state.gameOver();}

    Board scrambleBoard() {return current_state.scrambleBoard();}

    int getLives() {return current_state.getLives();}

    int getRow() {return row;}
    int getColumn() {return column;}
    int getValue() {return value;}



    }

