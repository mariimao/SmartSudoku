package use_case.pause_game;

/**
 * This class is the interface of the PauseGame output boundary. It is implemented by the PauseGamePresenter.
 */
public interface PauseGameOutputBoundary {

    /**
     * Called when PauseGame use case runs successfully - prepares a success view.
     * @param pauseGameOutputData is an PauseGameOutputData object
     */
    void prepareSuccessView(PauseGameOutputData pauseGameOutputData);

    /**
     * Called when PauseGame doesn't run successfully - prepares a fail view.
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);

}
