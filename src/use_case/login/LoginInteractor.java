package use_case.login;

import entity.user.User;

/**
 * Class representing the interactor for the Login usecase. This class implements the LoginInputBoundary.
 */
public class LoginInteractor implements LoginInputBoundary{

    private final LoginUserDataAccessInterface loginUserDataAccessInterface;
    private final LoginOutputBoundary loginPresenter;

    /**
     * Constructor for the LoginInteractor object.
     * @param loginUserDataAccessInterface is a LoginUserDataAccessInterface object
     * @param loginPresenter is a LoginOutputBoundary object
     */
    public LoginInteractor(LoginUserDataAccessInterface loginUserDataAccessInterface,
                            LoginOutputBoundary loginPresenter) {
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
        this.loginPresenter = loginPresenter;
    }

    /**
     * Executes the Login UseCase.
     * This function allows the user to log into the screen using an existing username and correct password.
     * If the user input it not valid, sends fail view.
     * @param loginInputData is an SignupInputData object
     */
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!loginUserDataAccessInterface.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String correct_password = loginUserDataAccessInterface.get(username).getPassword();
            if (!password.equals(correct_password)) {
                loginPresenter.prepareFailView("Incorrect password. Try again.");
            } else {

                User user = loginUserDataAccessInterface.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }



}
