package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartPresenter;
import interface_adapter.start.StartViewModel;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.start.StartInputBoundary;
import use_case.start.StartInteractor;
import use_case.start.StartOutputBoundary;
import use_case.start.StartUserDataAccessInterface;
import view.PausedGameView;

import javax.swing.*;
import java.io.IOException;


public class PausedGameUseCaseFactory {
    private PausedGameUseCaseFactory() {
    }

    public static PausedGameView create(ViewManagerModel viewManagerModel, PauseGameViewModel pauseGameViewModel,
                                        StartViewModel startViewModel, MenuViewModel menuViewModel, SignupViewModel signupViewModel,
                                        LoginViewModel loginViewModel, UserDAO userDataAccessObject) {
        try {
            StartController startController = createUserStartUseCase(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
            MenuController menuController = createUserMenuUseCase(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new PausedGameView(pauseGameViewModel, startViewModel, menuViewModel, viewManagerModel, startController, menuController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open page.");
        }

        return null;
    }

    private static StartController createUserStartUseCase(ViewManagerModel viewManagerModel, StartViewModel startViewModel,
                                                          SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                          StartUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        StartOutputBoundary startOutputBoundary = new StartPresenter(startViewModel, signupViewModel, loginViewModel, viewManagerModel);
        StartInputBoundary startInteractor = new StartInteractor(userDataAccessObject, startOutputBoundary);
        return new StartController(startInteractor);
    }

    private static MenuController createUserMenuUseCase(ViewManagerModel viewManagerModel, StartViewModel startViewModel,
                                                        MenuViewModel menuViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                        MenuUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        MenuOutputBoundary menuPresenter = new MenuPresenter();
        MenuInputBoundary menuInteractor = new MenuInteractor(userDataAccessObject, menuPresenter);
        return new MenuController(menuInteractor);
    }
}
