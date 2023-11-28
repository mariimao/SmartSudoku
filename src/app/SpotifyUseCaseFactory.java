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
import view.SpotifyView;

public class SpotifyUseCaseFactory {
    private SpotifyUseCaseFactory() {}

    public static SpotifyView create(ViewManagerModel viewManagerModel,
                                     SpotifyViewModel spotifyViewModel, SpotifyDAO spotifyDAO, NewGameViewModel newGameViewModel) {
        SpotifyController spotifyController = createUserSpotifyCase(viewManagerModel, spotifyViewModel, spotifyDAO, newGameViewModel);
        return new SpotifyView(spotifyViewModel, spotifyController, newGameViewModel);
    }

    private static SpotifyController createUserSpotifyCase(ViewManagerModel viewManagerModel, SpotifyViewModel spotifyViewModel, SpotifyDAO spotifyDAO, NewGameViewModel newGameViewModel) {
        SpotifyPresenter spotifyPresenter = new SpotifyPresenter(spotifyViewModel, newGameViewModel, viewManagerModel);
        SpotifyInputBoundary spotifyInteractor = new SpotifyInteractor(spotifyDAO, spotifyPresenter);
        return new SpotifyController(spotifyInteractor);
    }
}
