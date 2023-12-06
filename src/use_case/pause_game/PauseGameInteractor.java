package use_case.pause_game;

import entity.user.*;

/**
 * Class representing the interactor for the PauseGame usecase. This class implements the PauseGameInputBoundary.
 */
public class PauseGameInteractor implements PauseGameInputBoundary{
    final PauseGameDataAccessInterface pauseGameDataAccessInterface;
    final PauseGameOutputBoundary pauseGamePresenter;

    /**
     * Is a constructor for a PauseGameInteractor object.
     * @param pauseGameDataAccessInterface is a PauseGameDataAccessInterface object
     * @param pauseGamePresenter is a PauseGameOutputBoundary object
     */
    public PauseGameInteractor(PauseGameDataAccessInterface pauseGameDataAccessInterface, PauseGameOutputBoundary pauseGamePresenter) {
        this.pauseGameDataAccessInterface = pauseGameDataAccessInterface;
        this.pauseGamePresenter = pauseGamePresenter;
    }

    /**
     * Executes the PauseGame use case.
     * This method saves the user's current game so it can be loaded later. If this succeeds, it prepares the success
     * view. If not, then it calls the fail view.
     * @param pauseGameInputData is an PauseGameInputData object
     */
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
            pauseGamePresenter.prepareFailView("Data was not saved");
        }  // if not, show failed view
    }
}
