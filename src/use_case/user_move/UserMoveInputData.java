package use_case.user_move;

import entity.board.Board;
import entity.board.GameState;

public class UserMoveInputData {

    final public GameState current_state;
    final private int x;
    final private int y;
    final private int value;

    public UserMoveInputData(GameState currentState, int x, int y, int value) {
        current_state = currentState;
        this.x = x;
        this.y = y;
        this.value = value;

}

    Board getCurrent_board() {return current_state.getCurrBoard();}

    void loseLife() {current_state.loseLife();}

    Board makeMove() {return current_state.makeMove(x, y, value);}

    boolean gameOver() {return current_state.gameOver();}

    int getDifficulty() {return current_state.getDifficulty();}

    void scrambleBoard() {current_state.scrambleBoard();}

    int getLives() {return current_state.getLives();}

    int getX() {return x;}
    int getY() {return y;}
    int getValue() {return value;}



    }

