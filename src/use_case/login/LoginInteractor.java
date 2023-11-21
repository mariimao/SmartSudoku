package use_case.login;

import entity.user.User;

public class LoginInteractor implements LoginInputBoundary{

    private final LoginUserDataAccessInterface loginUserDataAccessInterface;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface loginUserDataAccessInterface,
                            LoginOutputBoundary loginPresenter) {
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
        this.loginPresenter = loginPresenter;
    }

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
