package use_case.end_game;

/**
 * This class is the interface of the EndGame output boundary. It is implemented by EndGamePresenter.
 */
public interface EndGameOutputBoundary {

    /**
     * Called when EndGame runs successfully - prepares a success view.
     *
     * @param endGameOutputData is an EndGameOutputData object
     */
    void prepareSuccessView(EndGameOutputData endGameOutputData);

    /**
     * Called when EndGame doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
