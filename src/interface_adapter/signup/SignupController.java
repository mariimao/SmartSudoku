package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * The class for SignupController. Acts as controller to send information to SignupInteractor.
 */
public class SignupController {

    final SignupInputBoundary signupInteractor;

    /**
     * Constructor for signupInteractor
     *
     * @param signupInteractor the interactor of signup use case that makes decisions
     */
    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     *
     * @param username       the username inputted by the user
     * @param password       the password inputted by the user
     * @param repeatpassword the repeated password inputted by the user to verify first password
     */
    public void execute(String username, String password, String repeatpassword) {
        SignupInputData signupInputData = new SignupInputData(username,
                password, repeatpassword);

        signupInteractor.execute(signupInputData);
    }
}
