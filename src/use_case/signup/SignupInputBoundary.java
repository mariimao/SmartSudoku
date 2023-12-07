package use_case.signup;

/**
 * Class representing the InputBoundary for the Signup usecase. This class is implemented by the SignupInteractor.
 */
public interface SignupInputBoundary {

    /**
     * Executes the Signup use case.
     *
     * @param signupInputData is a SignupInputData object
     */
    void execute(SignupInputData signupInputData);
}
