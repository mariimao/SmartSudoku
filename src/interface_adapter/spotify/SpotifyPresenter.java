package interface_adapter.spotify;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameViewModel;
import use_case.spotify.SpotifyOutputBoundary;
import use_case.spotify.SpotifyOutputData;

/**
 * Class of Presenter for spotify use case. Implements SpotifyOutputBoundary
 */
public class SpotifyPresenter implements SpotifyOutputBoundary {
    private final SpotifyViewModel spotifyViewModel;

    private final NewGameViewModel newGameViewModel;

    private ViewManagerModel viewManagerModel;

    /**
     * Constructor of Spotify Presenter
     * @param spotifyViewModel the view model for spotify use case, is SpotifyViewModel object
     * @param newGameViewModel the view model for newGame use case, is NewGameViewModel object
     * @param viewManagerModel the viewManagerModel for the app, changes the views
     */
    public SpotifyPresenter(SpotifyViewModel spotifyViewModel, NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.spotifyViewModel = spotifyViewModel;
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     *  Prepares the success view if use case is successful
     * @param spotifyOutputData is an SpotifyOutputData object
     */
    @Override
    public void prepareSuccessView(SpotifyOutputData spotifyOutputData) {
        SpotifyState spotifyState = this.spotifyViewModel.getSpotifyState();
        spotifyState.setSearchResults(spotifyOutputData.getSearchResults());
        this.spotifyViewModel.setSpotifyState(spotifyState);
        spotifyViewModel.firePropertyChanged();
    }

    /**
     * Prepares fail view for when use case is unsuccessful
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
        spotifyState.setSearchResultsError(error);
        spotifyViewModel.firePropertyChanged();
    }
}
