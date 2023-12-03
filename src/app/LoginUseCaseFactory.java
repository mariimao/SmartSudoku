package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.start.StartViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * Use case factory for the Login state.
 * This class creates the data for the views in a LoginView object.
 */
public class LoginUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private LoginUseCaseFactory() {
    }

    /**
     * Creates a new LoginView object. If the data file could not be opened, then the function throws an error.
     *
     * @param viewManagerModel     is a new ViewManagerModel object
     * @param loginViewModel       is a new LoginViewModel object
     * @param menuViewModel        is a new MenuViewModel object
     * @param playGameViewModel    is a new PlayGameViewModel object
     * @param pauseGameViewModel   is a new PauseGameViewModel object
     * @param resumeGameViewModel  is a new ResumeGameViewModel object
     * @param startViewModel       is a new StartViewModel object
     * @param userDataAccessObject is a new UserDataAccessObject
     * @return LoginView object, with parameters for newly created relevant models and controllers
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, MenuViewModel menuViewModel,
            PlayGameViewModel playGameViewModel, PauseGameViewModel pauseGameViewModel,
            ResumeGameViewModel resumeGameViewModel, StartViewModel startViewModel, UserDAO userDataAccessObject) {

        LoginController loginController = null;
        try {
            loginController = createUserSignupUseCase(viewManagerModel, loginViewModel, menuViewModel, userDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new LoginView(loginController, loginViewModel, playGameViewModel, pauseGameViewModel, resumeGameViewModel);
    }

    /**
     * Creates a LoginController
     * @param viewManagerModel
     * @param loginViewModel
     * @param menuViewModel
     * @param userDataAccessObject
     * @return
     * @throws IOException
     */
    private static LoginController createUserSignupUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                                           MenuViewModel menuViewModel, LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, menuViewModel, viewManagerModel);

        LoginInputBoundary userLoginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(userLoginInteractor);
    }
}
