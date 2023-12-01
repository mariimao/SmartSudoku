package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameViewModel;
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
import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGamePresenter;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInputBoundary;
import use_case.end_game.EndGameInteractor;
import use_case.end_game.EndGameOutputBoundary;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInteractor;
import view.EndGameView;
import view.NewGameView;

import java.io.IOException;

public class EndGameUseCaseFactory {
    private EndGameUseCaseFactory() {}

    public static EndGameView create(ViewManagerModel viewManagerModel, EndGameViewModel endGameViewModel, UserDAO userDataAccessObject, MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel, StartViewModel startViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        EndGameController endGameController = createUserEndGameUserCase(viewManagerModel, endGameViewModel, menuViewModel, leaderboardViewModel, userDataAccessObject);
        MenuController menuController = createUserMenuUseCase(viewManagerModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, userDataAccessObject);
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, userDataAccessObject);
        return new EndGameView(endGameViewModel, endGameController, viewManagerModel, menuController, menuViewModel, leaderboardController, leaderboardViewModel);
    }

    private static MenuController createUserMenuUseCase(ViewManagerModel viewManagerModel, StartViewModel startViewModel,
                                                        MenuViewModel menuViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                        MenuUserDataAccessInterface userDataAccessObject) {

        MenuOutputBoundary menuPresenter = new MenuPresenter(menuViewModel, viewManagerModel);
        MenuInputBoundary menuInteractor = new MenuInteractor(userDataAccessObject, menuPresenter);
        return new MenuController(menuInteractor);
    }
    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel);
        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface, leaderboardPresenter);
        return new LeaderboardController(leaderboardInteractor);

    }

    private static EndGameController createUserEndGameUserCase(ViewManagerModel viewManagerModel, EndGameViewModel endGameViewModel, MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel, EndGameDataAccessInterface endGameDataAccessInterface) {
        EndGameOutputBoundary endGamePresenter = new EndGamePresenter(leaderboardViewModel, menuViewModel, endGameViewModel, viewManagerModel);
        EndGameInputBoundary endGameInteractor = new EndGameInteractor(endGameDataAccessInterface, endGamePresenter);
        return new EndGameController(endGameInteractor);

    }
}
