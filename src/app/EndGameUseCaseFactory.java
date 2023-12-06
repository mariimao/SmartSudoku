package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuUserDataAccessInterface;
import view.EndGameView;

/**
 * Use case factory for the End Game state.
 * This class creates the data for the views in a EndGameView object, which are the menu state,
 * end game state, play game state, and leaderboard state.
 */
public class EndGameUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private EndGameUseCaseFactory() {
    }

    /**
     * Creates a new EndGameView object.
     *
     * @param viewManagerModel     is a new viewManagerModel object
     * @param endGameViewModel     is a new endGameViewModel object
     * @param userDataAccessObject is a new userDataAccess object
     * @param menuViewModel        is a new menuViewModel object
     * @param leaderboardViewModel is a new leaderboardViewModel object
     * @return EndGameView object, with parameters for newly created relevant models and controllers
     */
    public static EndGameView create(ViewManagerModel viewManagerModel, EndGameViewModel endGameViewModel, UserDAO userDataAccessObject, MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel) {

        EndGameController endGameController = createUserEndGameUserCase(viewManagerModel, endGameViewModel, menuViewModel, leaderboardViewModel, userDataAccessObject);
        MenuController menuController = createUserMenuUseCase(viewManagerModel, menuViewModel, userDataAccessObject);
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, menuViewModel,userDataAccessObject);
        return new EndGameView(endGameViewModel, endGameController, viewManagerModel, menuController, menuViewModel, leaderboardController, leaderboardViewModel, new PlayGameViewModel());
    }

    private static MenuController createUserMenuUseCase(ViewManagerModel viewManagerModel,
                                                        MenuViewModel menuViewModel,
                                                        MenuUserDataAccessInterface userDataAccessObject) {

        MenuPresenter menuPresenter = new MenuPresenter(menuViewModel, viewManagerModel);
        MenuInteractor menuInteractor = new MenuInteractor(userDataAccessObject, menuPresenter);
        return new MenuController(menuInteractor);
    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel, menuViewModel);
        LeaderboardInteractor leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface, leaderboardPresenter);
        return new LeaderboardController(leaderboardInteractor);

    }

    private static EndGameController createUserEndGameUserCase(ViewManagerModel viewManagerModel, EndGameViewModel endGameViewModel, MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel, EndGameDataAccessInterface endGameDataAccessInterface) {
        EndGamePresenter endGamePresenter = new EndGamePresenter(leaderboardViewModel, menuViewModel, endGameViewModel, viewManagerModel);
        EndGameInteractor endGameInteractor = new EndGameInteractor(endGameDataAccessInterface, endGamePresenter);
        return new EndGameController(endGameInteractor);

    }
}
