package app;

import data_access.UserDAO;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGamePresenter;
import interface_adapter.resume_game.ResumeGameViewModel;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInteractor;
import use_case.resume_game.ResumeGameOutputBoundary;
import view.MenuView;

import javax.swing.*;
import java.io.IOException;

/**
 * Use case factory for the Menu state.
 * This class creates the data for the views in a MenuView object, which are the menu state,
 * resume state, new game state, leaderboard state, and login state.
 */
public class MenuUseCaseFactory {

    /** Prevent instantiation. */
    private MenuUseCaseFactory() {}

    /**
     * Creates a new MenuView object. If the data file could not be opened, then the function throws an error.
     * @param viewManagerModel is a new ViewManagerModel object
     * @param menuViewModel is a new MenuViewModel object
     * @param resumeGameViewModel is a new ResumeGameViewModel object
     * @param loginViewModel is a new LoginViewModel object
     * @param newGameViewModel is a new NewGameViewModel object
     * @param userDataAccessObject is a new UserDataAccessObject
     * @param leaderboardViewModel is a new LeaderboardViewModel object
     * @param playGameViewModel is a new PlayGameViewModel object
     * @return MenuView object, with parameters for newly created relevant models and controllers
     */
    public static MenuView create(
            ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, ResumeGameViewModel resumeGameViewModel, LoginViewModel loginViewModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject,
            LeaderboardViewModel leaderboardViewModel, PlayGameViewModel playGameViewModel) {

        try {
            MenuController menuController = createUserSignupUseCase(viewManagerModel, menuViewModel, userDataAccessObject);
            ResumeGameController resumeGameController = createUserResumeCase(viewManagerModel, resumeGameViewModel, loginViewModel, userDataAccessObject, playGameViewModel);
            NewGameController newGameController = createUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
            LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, menuViewModel,userDataAccessObject);
            return new MenuView(menuController, menuViewModel, resumeGameController, resumeGameViewModel, newGameViewModel, newGameController, leaderboardViewModel, leaderboardController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NewGameController createUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }

    private static MenuController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                           MenuViewModel menuViewModel, UserDAO userDataAccessObject) throws IOException {

        MenuOutputBoundary menuOutputBoundary = new MenuPresenter(menuViewModel, viewManagerModel);

        MenuInteractor menuInteractor = new MenuInteractor(
                userDataAccessObject, menuOutputBoundary);

        return new MenuController(menuInteractor);
    }
    private static ResumeGameController createUserResumeCase(ViewManagerModel viewManagerModel,
                                                             ResumeGameViewModel resumeGameViewModel,
                                                             LoginViewModel loginViewModel,
                                                             ResumeGameDataAccessInterface resumeGameDataAccessInterface,
                                                             PlayGameViewModel playGameViewModel) {
        // Going into the LoginState to retrieve the player's username
        String username = loginViewModel.getLoginState().getUsername();  // ASSUMPTION: they have to already be logged in to hit the resume button
        User user = resumeGameDataAccessInterface.get(username);

        ResumeGameOutputBoundary resumeGamePresenter = new ResumeGamePresenter(resumeGameViewModel, viewManagerModel, playGameViewModel);
        ResumeGameInputBoundary resumeGameInteractor = new ResumeGameInteractor(resumeGameDataAccessInterface, resumeGamePresenter);
        return new ResumeGameController(resumeGameInteractor);
    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel,
                                                             LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel, LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel, menuViewModel);
        LeaderboardInteractor leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface,
                leaderboardOutputBoundary);

        return new LeaderboardController(leaderboardInteractor);
    }

}
