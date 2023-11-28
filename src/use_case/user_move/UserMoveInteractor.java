package use_case.user_move;

import entity.board.Board;
import use_case.login.LoginOutputData;

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

        if (userMoveInputData.gameOver()) {
            UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
            gamePresenter.prepareEndView(userMoveOutputData);
        } else {
            int[][] possibleValues = current_board.generatePossibleValues();
            if (current_board.valueNotAvailable(possibleValues, value, x, y)) {
                userMoveInputData.loseLife();
                if (userMoveInputData.getLives() == 0) {
                    UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                    gamePresenter.prepareEndView(userMoveOutputData);
                } else {
                    gamePresenter.prepareFailView("Incorrect input. Try again.");
                }
            } else {
                userMoveInputData.makeMove();
                userMoveInputData.scrambleBoard();
                UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                gamePresenter.prepareSuccessView(userMoveOutputData);
            }
        }
    }
}