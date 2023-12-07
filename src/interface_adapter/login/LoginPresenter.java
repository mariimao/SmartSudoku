package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;

/**
 * The LoginPresenter class. Implements LoginOutputBoundary.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final MenuViewModel menuViewModel;

    /**
     * Constructor for LoginPresenter
     * @param loginViewModel        is a LoginViewModel object
     * @param menuViewModel         is a MenuViewModel object
     * @param viewManagerModel      is a ViewManagerModel object
     */
    public LoginPresenter (LoginViewModel loginViewModel,
                           MenuViewModel menuViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.menuViewModel = menuViewModel;
    }

    /**
     * Prepares the success view. Changes to menu view
     * @param loginOutputData is an LoginOutputData object
     */
    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        MenuState menuState = menuViewModel.getMenuState();
        this.menuViewModel.setMenuState(menuState);
        menuViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view with a error message
     * @param error is a String containing a description of the error
     */
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
