package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter (SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

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
