package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

public class EndGameInteractor implements EndGameInputBoundary {
    final EndGameDataAccessInterface endGameDataAccessInterface;
    final EndGameOutputBoundary endGamePresenter;

    public EndGameInteractor(EndGameDataAccessInterface endGameDataAccessInterface, EndGameOutputBoundary endGamePresenter) {
        this.endGameDataAccessInterface = endGameDataAccessInterface;
        this.endGamePresenter = endGamePresenter;
    }

    @Override
    public void execute(EndGameInputData endGameInputData) {
        User user = endGameDataAccessInterface.get(endGameInputData.getUsername());
        Scores score = new Scores(endGameInputData.getTime(), endGameInputData.spacesLeft(), endGameInputData.getLives(), endGameInputData.isCompleted());
        user.setFinalGame(endGameInputData.getCurrent_state());
        boolean useCaseSuccess = endGameDataAccessInterface.setFinalGame(user);
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score, useCaseSuccess);
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
