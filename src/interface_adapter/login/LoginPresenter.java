package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final MenuViewModel menuViewModel;

    public LoginPresenter (LoginViewModel loginViewModel,
                           MenuViewModel menuViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        // changes to menu view
        menuViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getLoginState();
        if (error.equals("Username already exists. Please pick a different username.")){
            loginState.setUsernameError(error);
        }
        else {
            loginState.setPasswordError(error);
        }
    }
}
