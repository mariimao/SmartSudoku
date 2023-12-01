package use_case.user_move;

import entity.board.Board;
import use_case.login.LoginOutputData;

public class UserMoveInteractor implements UserMoveInputBoundary {

    final UserMoveDataAccessInterface userMoveDataAccessInterface;
    final UserMoveOutputBoundary easyGamePresenter;

    public UserMoveInteractor(UserMoveDataAccessInterface userMoveDataAccessInterface, UserMoveOutputBoundary easyGamePresenter) {
        this.userMoveDataAccessInterface = userMoveDataAccessInterface;
        this.easyGamePresenter = easyGamePresenter;
    }

    public void execute(UserMoveInputData userMoveInputData) {
        Board current_board = userMoveInputData.getCurrent_board();
        int row = userMoveInputData.getRow();
        int column = userMoveInputData.getColumn();
        int value = userMoveInputData.getValue();

        if (current_board.correctMove(row, column, value)) {
            userMoveInputData.loseLife();
            if (userMoveInputData.getLives() < 1) {
                UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                easyGamePresenter.prepareEndView(userMoveOutputData);
            } else {
                easyGamePresenter.prepareFailView("Incorrect input. Try again.");
            }
        } else {
            userMoveInputData.makeMove();
            if (userMoveInputData.gameOver()) {
                UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                easyGamePresenter.prepareEndView(userMoveOutputData);
            } else {
                userMoveInputData.scrambleBoard();
                UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                easyGamePresenter.prepareSuccessView(userMoveOutputData);
            }
        }
    }
}