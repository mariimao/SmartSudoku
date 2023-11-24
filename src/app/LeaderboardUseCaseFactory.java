package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import view.LeaderboardView;

import javax.swing.*;
import java.io.IOException;

public class LeaderboardUseCaseFactory {
    private LeaderboardUseCaseFactory() {}

    public static LeaderboardView create(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, UserDAO userDataAccessObject) {
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, userDataAccessObject);
        return new LeaderboardView(leaderboardViewModel, leaderboardController);

    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel);
        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface, leaderboardPresenter);
        return new LeaderboardController(leaderboardInteractor);

    }
}
