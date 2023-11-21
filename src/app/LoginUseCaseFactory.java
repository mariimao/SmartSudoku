package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.signup.cancel.CancelPresenter;
import interface_adapter.signup.cancel.CancelViewModel;
import interface_adapter.start.StartViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.cancel.CancelInputBoundary;
import use_case.signup.cancel.CancelInteractor;
import use_case.signup.cancel.CancelOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, MenuViewModel menuViewModel,
            CancelViewModel cancelViewModel, StartViewModel startViewModel,
            UserDAO userDataAccessObject) {

        try {
            LoginController loginController = createUserSignupUseCase(viewManagerModel, loginViewModel, menuViewModel, userDataAccessObject);
            CancelController cancelController = createUserClearUseCase(viewManagerModel, cancelViewModel, startViewModel);
            return new LoginView(loginController, loginViewModel, cancelController);
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

    private static CancelController createUserClearUseCase(ViewManagerModel viewManagerModel,
                                                           CancelViewModel cancelViewModel, StartViewModel startViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CancelOutputBoundary clearOutputBoundary = new CancelPresenter(cancelViewModel, startViewModel, viewManagerModel);

        CancelInputBoundary cancelInteractor = new CancelInteractor(
                clearOutputBoundary);

        return new CancelController(cancelInteractor);
    }
}
