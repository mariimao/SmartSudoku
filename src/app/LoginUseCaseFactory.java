package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.start.StartViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, MenuViewModel menuViewModel,
            StartViewModel startViewModel,
            UserDAO userDataAccessObject) {

        try {
            LoginController loginController = createUserSignupUseCase(viewManagerModel, loginViewModel, menuViewModel, userDataAccessObject);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserSignupUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                                            MenuViewModel menuViewModel, LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter( loginViewModel, menuViewModel, viewManagerModel);

        LoginInputBoundary userLoginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(userLoginInteractor);
    }
}
