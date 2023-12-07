package interface_adapter.start;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.start.StartOutputBoundary;
import use_case.start.StartOutputData;

/**
 * Class of Presenter for start use case. Implements StartOutputBoundary
 */
public class StartPresenter implements StartOutputBoundary {

    private final StartViewModel startViewModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for StartPresenter
     *
     * @param startViewModel   the view model for start, is StartViewModel object
     * @param signupViewModel  the signup View model, is SignupViewModel object
     * @param loginViewModel   the viewmodel for login, is LoginViewModel object
     * @param viewManagerModel the viewMangerModel that manages the views
     */
    public StartPresenter(StartViewModel startViewModel, SignupViewModel signupViewModel,
                          LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.startViewModel = startViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    /**
     * Prepares the success view if use case is successful
     *
     * @param startOutputData is the output data that is sent to presenter
     */
    @Override
    public void prepareSuccessView(StartOutputData startOutputData) {

        // if signup is pressed, change to signup page
        if (startOutputData.getInteracton().equals("Signup")) {
            SignupState signupState = signupViewModel.getSignupState();
            this.signupViewModel.setSignupState(signupState);
            signupViewModel.firePropertyChanged();

            viewManagerModel.setActiveViewName(signupViewModel.getViewName());
        }
        // otherwise, change to log in page
        else if (startOutputData.getInteracton().equals("Login")) {
            LoginState loginState = loginViewModel.getLoginState();
            this.loginViewModel.setLoginState(loginState);
            loginViewModel.firePropertyChanged();

            viewManagerModel.setActiveViewName(loginViewModel.getViewName());
        }

        viewManagerModel.firePropertyChanged();


    }

    /**
     * Prepares fail view for when use case is unsuccessful
     *
     * @param error the String that is displayed to use about the error
     */
    public void prepareFailView(String error) {
        startViewModel.firePropertyChanged();
    }
}
