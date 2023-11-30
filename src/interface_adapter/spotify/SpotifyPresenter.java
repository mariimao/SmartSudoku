package interface_adapter.spotify;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameViewModel;
import use_case.spotify.SpotifyOutputBoundary;
import use_case.spotify.SpotifyOutputData;

public class SpotifyPresenter implements SpotifyOutputBoundary {
    private final SpotifyViewModel spotifyViewModel;

    private final NewGameViewModel newGameViewModel;

    private ViewManagerModel viewManagerModel;

    public SpotifyPresenter(SpotifyViewModel spotifyViewModel, NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.spotifyViewModel = spotifyViewModel;
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SpotifyOutputData spotifyOutputData) {
        SpotifyState spotifyState = this.spotifyViewModel.getSpotifyState();
        spotifyState.setSearchResults(spotifyOutputData.getSearchResults());
        this.spotifyViewModel.setSpotifyState(spotifyState);
        spotifyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
        spotifyState.setSearchResultsError(error);
        spotifyViewModel.firePropertyChanged();
    }
}
