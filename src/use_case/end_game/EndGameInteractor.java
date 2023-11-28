package use_case.end_game;

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
        int score = 0; // TODO: change this
        // akunna: i had to make score point to something and also change the user name into a user
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score);
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
