package app;

import data_access.SpotifyDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.spotify.SpotifyController;
import interface_adapter.spotify.SpotifyPresenter;
import interface_adapter.spotify.SpotifyViewModel;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.spotify.SpotifyInputBoundary;
import use_case.spotify.SpotifyInteractor;
import view.NewGameView;

public class NewGameUseCaseFactory {
    private NewGameUseCaseFactory() {}

    public static NewGameView create(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject,
                                     SpotifyViewModel spotifyViewModel, SpotifyDAO spotifyDAO) {
        // TODO: Update these lines so that it includes the viewmodels that include the views for the games, leaderboard, etc.
        NewGameController newGameController = createUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
        SpotifyController spotifyController = createUserSpotifyCase(viewManagerModel, spotifyViewModel, spotifyDAO, newGameViewModel);
        return new NewGameView(newGameViewModel, newGameController, spotifyViewModel, spotifyController);
    }

    private static SpotifyController createUserSpotifyCase(ViewManagerModel viewManagerModel, SpotifyViewModel spotifyViewModel, SpotifyDAO spotifyDAO, NewGameViewModel newGameViewModel) {
        SpotifyPresenter spotifyPresenter = new SpotifyPresenter(spotifyViewModel, newGameViewModel, viewManagerModel);
        SpotifyInputBoundary spotifyInteractor = new SpotifyInteractor(spotifyDAO, spotifyPresenter);
        return new SpotifyController(spotifyInteractor);
    }

    private static NewGameController createUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }
}
