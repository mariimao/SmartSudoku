package use_case.pause_game;

import entity.user.*;

public class PauseGameInteractor implements PauseGameInputBoundary{
    final PauseGameDataAccessInterface pauseGameDataAccessInterface;
    final PauseGameOutputBoundary pauseGamePresenter;

    public PauseGameInteractor(PauseGameDataAccessInterface pauseGameDataAccessInterface, PauseGameOutputBoundary pauseGamePresenter) {
        this.pauseGameDataAccessInterface = pauseGameDataAccessInterface;
        this.pauseGamePresenter = pauseGamePresenter;
    }

    @Override
    public void execute(PauseGameInputData pauseGameInputData) {
        User user = pauseGameDataAccessInterface.get(pauseGameInputData.getUsername());
        user.setPausedGame(pauseGameInputData.getCurrent_state());
        boolean useCaseSuccess = pauseGameDataAccessInterface.setProgress(user);  // try and save the User's current game
        PauseGameOutputData pauseGameOutputData = new PauseGameOutputData(user, useCaseSuccess);  // create OutputData
        if (useCaseSuccess) {
            pauseGamePresenter.prepareSuccessView(pauseGameOutputData);
        }  // if it was saved show success view
        else {
            pauseGamePresenter.prepareSuccessView(pauseGameOutputData);
        }  // if not, show failed view
    }
}
