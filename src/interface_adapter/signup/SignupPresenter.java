package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The SignupPresenter that sends output data to the viewmodel and then view.
 * Implements SignupOutputBoundary
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for SignupPresenter
     * @param signupViewModel the signup view model that controls the view, is SignUpViewModel Object
     * @param loginViewModel the login view model that controllers the login view, is LoginViewModel object
     * @param viewManagerModel the viewManagerModel that manages the different views.
     */
    public SignupPresenter (SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    /**
     * Prepares the success view if use case is successful
     * @param signupOutputData is an SignupOutputData object
     */
    @Override
    public void prepareSuccessView(SignupOutputData signupOutputData) {
        // otherwise, change to log in page
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsername(signupOutputData.getUsername());
        this.loginViewModel.setLoginState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

    /**
     * Prepares the fail view if use case fails
     * @param error is a String containing a description of the error
     */
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getSignupState();
        if (error.equals("Username already exists. Please pick a different username.")){
            signupState.setUsernameError(error);
        }
        else {
            signupState.setPasswordError(error);
        }
        signupViewModel.firePropertyChanged();
    }

}
