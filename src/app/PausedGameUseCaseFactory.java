package app;

import data_access.SpotifyDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGamePresenter;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartPresenter;
import interface_adapter.start.StartViewModel;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.play_music.PlayMusicDataAccessInterface;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInteractor;
import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.start.StartInputBoundary;
import use_case.start.StartInteractor;
import use_case.start.StartOutputBoundary;
import use_case.start.StartUserDataAccessInterface;
import view.PausedGameView;

import javax.swing.*;
import java.io.IOException;

/**
 * Use case factory for the PausedGame state.
 * This class creates the data for the views in a PauseGameView object, which are the pause game state,
 * start state, menu state, resume game state, and play game state.
 */
public class PausedGameUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private PausedGameUseCaseFactory() {
    }

    /**
     * Creates a new PausedGameView object. If the object could not be created, an error will be
     * shown using a JDialog.
     *
     * @param viewManagerModel     is a ViewManagerModel object
     * @param pauseGameViewModel   is a PauseGameViewModel object
     * @param startViewModel       is a StartViewModel object
     * @param menuViewModel        is a MenuViewModel object
     * @param signupViewModel      is a SignupViewModel object
     * @param loginViewModel       is a LoginViewModel object
     * @param resumeGameViewModel  is a ResumeGameViewModel object
     * @param playGameViewModel    is a PlayGameViewModel object
     * @param userDataAccessObject is a UserDataAccessObject
     * @return PausedGameView object, with parameters for newly created relevant models and controllers
     */
    public static PausedGameView create(ViewManagerModel viewManagerModel, PauseGameViewModel pauseGameViewModel,
                                        StartViewModel startViewModel, MenuViewModel menuViewModel,
                                        SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                        ResumeGameViewModel resumeGameViewModel, PlayGameViewModel playGameViewModel,
                                        UserDAO userDataAccessObject, SpotifyDAO spotifyDAO) {
        try {
            StartController startController = createUserStartUseCase(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
            MenuController menuController = createUserMenuUseCase(viewManagerModel, menuViewModel, userDataAccessObject);
            ResumeGameController resumeGameController = createUserResumeCase(viewManagerModel, resumeGameViewModel, userDataAccessObject, spotifyDAO, playGameViewModel);
            return new PausedGameView(pauseGameViewModel, startViewModel, menuViewModel, viewManagerModel, resumeGameViewModel, startController, menuController, resumeGameController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open page.");
        }

        return null;
    }

    private static ResumeGameController createUserResumeCase(ViewManagerModel viewManagerModel,
                                                             ResumeGameViewModel resumeGameViewModel,
                                                             ResumeGameDataAccessInterface resumeGameDataAccessInterface,
                                                             PlayMusicDataAccessInterface playMusicDataAccessInterface,
                                                             PlayGameViewModel playGameViewModel) {
        ResumeGameOutputBoundary resumeGamePresenter = new ResumeGamePresenter(resumeGameViewModel, viewManagerModel, playGameViewModel);
        ResumeGameInputBoundary resumeGameInteractor = new ResumeGameInteractor(resumeGameDataAccessInterface, playMusicDataAccessInterface, resumeGamePresenter);
        return new ResumeGameController(resumeGameInteractor);
    }

    private static StartController createUserStartUseCase(ViewManagerModel viewManagerModel, StartViewModel startViewModel,
                                                          SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                          StartUserDataAccessInterface userDataAccessObject) throws IOException {

        StartOutputBoundary startOutputBoundary = new StartPresenter(startViewModel, signupViewModel, loginViewModel, viewManagerModel);
        StartInputBoundary startInteractor = new StartInteractor(userDataAccessObject, startOutputBoundary);
        return new StartController(startInteractor);
    }

    private static MenuController createUserMenuUseCase(ViewManagerModel viewManagerModel,
                                                        MenuViewModel menuViewModel,
                                                        MenuUserDataAccessInterface userDataAccessObject) throws IOException {

        MenuOutputBoundary menuPresenter = new MenuPresenter(menuViewModel, viewManagerModel);
        MenuInputBoundary menuInteractor = new MenuInteractor(userDataAccessObject, menuPresenter);
        return new MenuController(menuInteractor);
    }
}
