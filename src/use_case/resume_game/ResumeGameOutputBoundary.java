package use_case.resume_game;

/**
 * This class is the interface of the resume game output boundary. It is implemented by ResumeGamePresenter.
 */
public interface ResumeGameOutputBoundary {

    /**
     * Called when resume game runs successfully - prepares a success view.
     *
     * @param resumeGameOutputData is an ResumeDataOutputData object
     */
    void prepareSuccessView(ResumeGameOutputData resumeGameOutputData);

    /**
     * Called when resume game doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */

    void prepareFailView(String error);
}
