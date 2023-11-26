package interface_adapter.easy_game;

import entity.board.GameState;
import use_case.user_move.UserMoveInputBoundary;
import use_case.user_move.UserMoveInputData;

public class EasyGameController {
    final UserMoveInputBoundary userMoveInteractor;

    public EasyGameController(UserMoveInputBoundary userMoveInteractor) {
        this.userMoveInteractor = userMoveInteractor;
    }


    public void execute(GameState currentState, int row, int column, int value) {
        UserMoveInputData userMoveInputData = new UserMoveInputData(currentState, row, column, value);
        userMoveInteractor.execute(userMoveInputData);
    }
}
