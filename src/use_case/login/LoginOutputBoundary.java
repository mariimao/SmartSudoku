package use_case.login;

/**
 * This class is the interface of the Login output boundary. It is implemented by LoginPresenter.
 */
public interface LoginOutputBoundary {

    /**
     * Called when Login runs successfully - prepares a success view.
     *
     * @param loginOutputData is an LoginOutputData object
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Called when Login doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);


}
