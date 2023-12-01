package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;

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
        MenuState menuState = menuViewModel.getMenuState();
        this.menuViewModel.setMenuState(menuState);
        menuViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getLoginState();
        if (error.equals("Incorrect password. Try again.")){
            loginState.setPasswordError(error);
        }
        else {
            loginState.setUsernameError(error);
            JOptionPane.showMessageDialog(null, "User Does Not Exist. Go To Signup Page");
        }
    }
}
