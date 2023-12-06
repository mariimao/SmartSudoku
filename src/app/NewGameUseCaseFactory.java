package app;

import data_access.SpotifyDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;

import interface_adapter.play_music.PlayMusicController;
import interface_adapter.play_music.PlayMusicPresenter;
import interface_adapter.spotify.SpotifyController;
import interface_adapter.spotify.SpotifyPresenter;
import interface_adapter.spotify.SpotifyViewModel;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.play_music.PlayMusicDataAccessInterface;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInteractor;
import use_case.spotify.SpotifyDataAccessInterface;
import use_case.spotify.SpotifyInputBoundary;
import use_case.spotify.SpotifyInteractor;

import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGamePresenter;
import interface_adapter.play_game.PlayGameViewModel;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInteractor;
import view.NewGameView;

/**
 * Use case factory for the NewGame state.
 * This class creates the data for the views in a NewGameView object, which are the new game state,
 * play game state, spotify state, and login state.
 */
public class NewGameUseCaseFactory {

    /** Prevent instantiation. */
    private NewGameUseCaseFactory() {}

    /**
     * Creates a new NewGameView object.
     * @param viewManagerModel is a new ViewManagerModel object
     * @param newGameViewModel is a new NewGameViewModel object
     * @param userDataAccessObject is a new UserDataAccessObject
     * @param playGameViewModel is a new PlayGameViewModel object
     * @param loginViewModel is a new LoginViewModel object
     * @param spotifyViewModel is a new SpotifyViewModel object
     * @param spotifyDAO is a new SpotifyDAO object
     * @return NewGameView object, with parameters for newly created relevant models and controllers
     */
    public static NewGameView create(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject, PlayGameViewModel playGameViewModel, LoginViewModel loginViewModel,
                                     SpotifyViewModel spotifyViewModel, SpotifyDAO spotifyDAO) {
        NewGameController newGameController = createUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
        PlayMusicController playMusicController = playMusicControllerUseCase(viewManagerModel,newGameViewModel, spotifyDAO);
        PlayGameController playGameController = createUserPlayGameUseCase(viewManagerModel, playGameViewModel, userDataAccessObject);
        SpotifyController spotifyController = createUserSpotifyCase(viewManagerModel, spotifyViewModel, spotifyDAO, newGameViewModel);
        return new NewGameView(newGameViewModel, newGameController, playGameViewModel, playGameController, spotifyViewModel, spotifyController, loginViewModel, playMusicController);
    }

    private static SpotifyController createUserSpotifyCase(ViewManagerModel viewManagerModel, SpotifyViewModel spotifyViewModel, SpotifyDataAccessInterface spotifyDAO, NewGameViewModel newGameViewModel) {
        SpotifyPresenter spotifyPresenter = new SpotifyPresenter(spotifyViewModel, newGameViewModel, viewManagerModel);
        SpotifyInputBoundary spotifyInteractor = new SpotifyInteractor(spotifyDAO, spotifyPresenter);
        return new SpotifyController(spotifyInteractor);
    }

    private static NewGameController createUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }
    private static PlayGameController createUserPlayGameUseCase(ViewManagerModel viewManagerModel,
                                                                PlayGameViewModel playGameViewModel,
                                                                PlayGameDataAccessInterface playGameDataAccessInterface) {
        PlayGamePresenter playGamePresenter = new PlayGamePresenter(playGameViewModel, viewManagerModel);
        PlayGameInputBoundary playGameInteractor = new PlayGameInteractor(playGameDataAccessInterface, playGamePresenter);
        return new PlayGameController(playGameInteractor);
    }

    private static PlayMusicController playMusicControllerUseCase(ViewManagerModel viewManagerModel,
                                                                NewGameViewModel newGameViewModel,
                                                                PlayMusicDataAccessInterface playMusicDataAccessInterface) {
        PlayMusicPresenter playMusicPresenter = new PlayMusicPresenter(newGameViewModel, viewManagerModel);
        PlayMusicInputBoundary playMusicInteractor = new PlayMusicInteractor(playMusicDataAccessInterface, playMusicPresenter);
        return new PlayMusicController(playMusicInteractor);
    }
}
