package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.signup.cancel.CancelPresenter;
import interface_adapter.signup.cancel.CancelViewModel;
import interface_adapter.start.StartViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.signup.cancel.CancelInputBoundary;
import use_case.signup.cancel.CancelInteractor;
import use_case.signup.cancel.CancelOutputBoundary;
import use_case.start.StartUserDataAccessInterface;
import view.LoginView;
import view.MenuView;

import javax.swing.*;
import java.io.IOException;

public class MenuUseCaseFactory {
    private MenuUseCaseFactory() {}

    public static MenuView create(
            ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, UserDAO userDataAccessObject) {

        try {
            // TODO: Update these lines so that it includes the viewmodels that include the views for the games, leaderboard, etc.

            MenuController menuController = createUserSignupUseCase(viewManagerModel, menuViewModel, userDataAccessObject);
            return new MenuView(menuController, menuViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                           MenuViewModel menuViewModel, UserDAO userDataAccessObject) throws IOException {

        MenuOutputBoundary menuOutputBoundary = new MenuPresenter();

        MenuInputBoundary menuInteractor = new MenuInteractor(
                userDataAccessObject, menuOutputBoundary);

        return new LoginController(menuInteractor);
    }

}
