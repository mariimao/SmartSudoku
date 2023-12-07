package use_case.signup;

/**
 * This class is the interface of the Signup output boundary. It is implemented by SignupPresenter.
 */
public interface SignupOutputBoundary {

    /**
     * Called when Signup runs successfully - prepares a success view.
     *
     * @param signupOutputData is an SignupOutputData object
     */
    void prepareSuccessView(SignupOutputData signupOutputData);

    /**
     * Called when Signup doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
