package use_case.start;

/**
 * This class is the interface of the start output boundary. It is implemented by StartPresenter.
 */
public interface StartOutputBoundary {

    /**
     * Called when the start use case runs successfully - prepares a success view.
     */
    void prepareSuccessView(StartOutputData startOutputData);

    /**
     * Called when the start use case doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */

    void prepareFailView(String error);
}
