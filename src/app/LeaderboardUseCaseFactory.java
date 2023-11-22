package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.cancel.CancelController;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import view.LeaderboardView;

public class LeaderboardUseCaseFactory {
    private LeaderboardUseCaseFactory() {}

    public static LeaderboardView create(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel, UserDAO userDataAccessObject) {
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, menuViewModel, userDataAccessObject);
        return new LeaderboardView(menuViewModel, leaderboardViewModel, leaderboardController);
    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,  LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel, menuViewModel);
        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface, leaderboardPresenter);
        return new LeaderboardController(leaderboardInteractor);

    }
}
