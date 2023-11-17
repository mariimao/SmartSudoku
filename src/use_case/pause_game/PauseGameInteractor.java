package use_case.pause_game;

import entity.User;

public class PauseGameInteractor implements PauseGameInputBoundary{
    final PauseGameDataAccessInterface pauseGameDataAccessInterface;
    final PauseGameOutputBoundary pauseGamePresenter;
    final User user;

    public PauseGameInteractor(PauseGameDataAccessInterface pauseGameDataAccessInterface, PauseGameOutputBoundary pauseGamePresenter, User user) {
        this.pauseGameDataAccessInterface = pauseGameDataAccessInterface;
        this.pauseGamePresenter = pauseGamePresenter;
        this.user = user;
    }

    @Override
    public void execute(PauseGameInputData pauseGameInputData) {
        boolean useCaseSuccess = pauseGameDataAccessInterface.saveProgress(user);  // try and save the User's current game
        PauseGameOutputData pauseGameOutputData = new PauseGameOutputData(user, !useCaseSuccess);  // create OutputData
        if (useCaseSuccess) {pauseGamePresenter.prepareSuccessView(pauseGameOutputData);}  // if it was saved show success view
        else {pauseGamePresenter.prepareSuccessView(pauseGameOutputData);}  // if not, show failed view
    }
}
