package use_case.user_move;

import entity.Board;
import entity.GameState;

public class UserMoveInputData {

    final private GameState current_state;

    public UserMoveInputData(GameState currentState) {
        current_state = currentState;
}

    Board getCurrent_board() {return current_state.getCurrBoard();}
    int getLives() {return current_state.getLives();}
    }
