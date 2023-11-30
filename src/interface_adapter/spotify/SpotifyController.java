package interface_adapter.spotify;

import use_case.spotify.SpotifyInputBoundary;
import use_case.spotify.SpotifyInputData;

public class SpotifyController {
    final SpotifyInputBoundary spotifyInteractor;

    public SpotifyController(SpotifyInputBoundary spotifyInteractor) {
        this.spotifyInteractor = spotifyInteractor;
    }

    public void execute(String searchKey) {
        SpotifyInputData spotifyInputData = new SpotifyInputData(searchKey);
        spotifyInteractor.execute(spotifyInputData);
    }
}
