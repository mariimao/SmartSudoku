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

/**
 * Use case factory for the Leaderboard state.
 * This class creates the data for the views in a LeaderboardView object.
 */
public class LeaderboardUseCaseFactory {

    /** Prevent instantiation. */
    private LeaderboardUseCaseFactory() {}

    /**
     *
     * @param viewManagerModel is a ViewManagerModel object
     * @param leaderboardViewModel is a LeaderboardViewModel object
     * @param userDataAccessObject is a UserDataAccessObject
     * @return LeaderboardView object, with parameters for newly created relevant models and controllers
     */
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
