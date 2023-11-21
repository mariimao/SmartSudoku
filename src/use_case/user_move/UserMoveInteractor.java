package use_case.user_move;

import entity.Board;
import entity.GameState;
import entity.User;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class UserMoveInteractor implements UserMoveInputBoundary {

    final UserMoveDataAccessInterface userMoveDataAccessInterface;
    final UserMoveOutputBoundary gamePresenter;

    public UserMoveInteractor(UserMoveDataAccessInterface userMoveDataAccessInterface, UserMoveOutputBoundary gamePresenter) {
        this.userMoveDataAccessInterface = userMoveDataAccessInterface;
        this.gamePresenter = gamePresenter;
    }

    public void execute(UserMoveInputData userMoveInputData) {
        Board current_board = userMoveInputData.getCurrent_board();
        int difficulty = userMoveInputData.getDifficulty();
        int x = userMoveInputData.getX();
        int y = userMoveInputData.getY();
        int value = userMoveInputData.getValue();

        if (userMoveInputData.gameOver()) {
            gamePresenter.prepareEndView(userMoveOutputData);
        } else {
            if (difficulty == 1) {
                int[][] easyPossibleValues = current_board.generatePossibleValues();
                if (current_board.valueNotAvailable(easyPossibleValues, value, x, y)) {
                    userMoveInputData.loseLife();
                    gamePresenter.prepareFailView("Incorrect input. Try again.");
                } else {
                    Board new_board = userMoveInputData.makeMove();
                    gamePresenter.prepareSuccessView(userMoveOutputData);
                }
            } else {
                int[][] hardPossibleValues = current_board.generatePossibleValues();
                if (current_board.valueNotAvailable(hardPossibleValues, value, x, y)) {
                    userMoveInputData.loseLife();
                    gamePresenter.prepareFailView("Incorrect input. Try again.");
                } else if (userMoveInputData.gameOver()) {
                    gamePresenter.prepareEndView(userMoveOutputData);
                } else {
                    userMoveInputData.makeMove();
                    gamePresenter.prepareSuccessView(userMoveOutputData);
                }
            }
        }
    }
}