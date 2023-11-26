package use_case.user_move;

import entity.board.GameState;

public interface UserMoveDataAccessInterface {

    GameState saveBoard(GameState gameState);

}
