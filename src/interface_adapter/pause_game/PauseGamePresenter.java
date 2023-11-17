package interface_adapter.pause_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

public class PauseGamePresenter implements PauseGameOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public PauseGamePresenter(SignupViewModel signupViewModel,
                              LoginViewModel loginViewModel,
                              ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PauseGameOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
