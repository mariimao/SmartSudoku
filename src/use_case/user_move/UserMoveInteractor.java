package use_case.user_move;

import entity.board.Board;
import use_case.login.LoginOutputData;

import java.io.IOException;

public class UserMoveInteractor implements UserMoveInputBoundary {

    final UserMoveDataAccessInterface userMoveDataAccessInterface;
    final UserMoveOutputBoundary easyGamePresenter;
    final UserMoveBoardDataAccessInterface userMoveBoardDataAccessInterface;

    public UserMoveInteractor(UserMoveDataAccessInterface userMoveDataAccessInterface,
                              UserMoveBoardDataAccessInterface userMoveBoardDataAccessInterface,
                              UserMoveOutputBoundary easyGamePresenter) {
        this.userMoveDataAccessInterface = userMoveDataAccessInterface;
        this.userMoveBoardDataAccessInterface = userMoveBoardDataAccessInterface;
        this.easyGamePresenter = easyGamePresenter;
    }

    public void execute(UserMoveInputData userMoveInputData) throws IOException {
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
                userMoveDataAccessInterface.saveBoard(userMoveInputData.current_state); // save board before or after scrambling?
                if (userMoveInputData.current_state.getDifficulty() == 1) {
                    userMoveInputData.scrambleBoard();
                    UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                    easyGamePresenter.prepareSuccessView(userMoveOutputData);
                } else { // hard mode
                    int correct_moves = userMoveInputData.current_state.getPastStates().size();
                    userMoveInputData.current_state.getCurrBoard().setBoard(userMoveBoardDataAccessInterface.convertToHashMap(userMoveBoardDataAccessInterface.generateBoard(correct_moves))); // returns int [][]

                    UserMoveOutputData userMoveOutputData = new UserMoveOutputData(userMoveInputData.current_state);
                    easyGamePresenter.prepareSuccessView(userMoveOutputData);
                }
            }
        }
    }
}