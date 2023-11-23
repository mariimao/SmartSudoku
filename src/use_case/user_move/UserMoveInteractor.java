package use_case.user_move;

import entity.Board;

public class UserMoveInteractor implements UserMoveInputBoundary {

    final UserMoveDataAccessInterface userMoveDataAccessInterface;
    final UserMoveOutputBoundary gamePresenter;

    public UserMoveInteractor(UserMoveDataAccessInterface userMoveDataAccessInterface, UserMoveOutputBoundary gamePresenter) {
        this.userMoveDataAccessInterface = userMoveDataAccessInterface;
        this.gamePresenter = gamePresenter;
    }

    public void execute(UserMoveInputData userMoveInputData) {
        Board current_board = (Board) userMoveInputData.getCurrent_board();
        int difficulty = userMoveInputData.getDifficulty();
        int x = userMoveInputData.getX();
        int y = userMoveInputData.getY();
        int value = userMoveInputData.getValue();

        UserMoveOutputData userMoveOutputData = null;
        if (userMoveInputData.gameOver()) {
            gamePresenter.prepareEndView(userMoveOutputData);
        } else {
            if (difficulty == 1) {
                int[][] easyPossibleValues = current_board.generatePossibleValues();
                if (current_board.valueNotAvailable(easyPossibleValues, value, x, y)) {
                    userMoveInputData.loseLife();
                    if (userMoveInputData.getLives() == 0) {
                        gamePresenter.prepareEndView(userMoveOutputData);
                    } else {
                        gamePresenter.prepareFailView("Incorrect input. Try again.");
                    }
                } else {
                    userMoveInputData.makeMove();
                    userMoveInputData.scrambleBoard();
                    gamePresenter.prepareSuccessView(userMoveOutputData);
                }
            } else {
                int[][] hardPossibleValues = current_board.generatePossibleValues();
                if (current_board.valueNotAvailable(hardPossibleValues, value, x, y)) {
                    userMoveInputData.loseLife();
                    if (userMoveInputData.getLives() == 0) {
                        gamePresenter.prepareEndView(userMoveOutputData);
                    } else {
                        gamePresenter.prepareFailView("Incorrect input. Try again.");
                    }
                } else {
                    userMoveInputData.makeMove();
                    userMoveInputData.scrambleBoard();
                    gamePresenter.prepareSuccessView(userMoveOutputData);
                }
            }
        }
    }
}