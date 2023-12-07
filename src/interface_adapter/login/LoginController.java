package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The Controller for Login. Creates input data to be used by the controller.
 */
public class LoginController {

    private final LoginInputBoundary loginInteractor;

    /**
     * The constructor for LoginController
     * @param loginInteractor   is a LoginInputBoundary type
     */
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * Create the input data and calls the interactor
     * @param username      the username they entered
     * @param password      the password they entered
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }
}
