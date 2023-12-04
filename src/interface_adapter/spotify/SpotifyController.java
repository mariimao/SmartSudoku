package interface_adapter.spotify;

import use_case.spotify.SpotifyInputBoundary;
import use_case.spotify.SpotifyInputData;

import java.io.IOException;

public class SpotifyController {
    final SpotifyInputBoundary spotifyInteractor;

    public SpotifyController(SpotifyInputBoundary spotifyInteractor) {
        this.spotifyInteractor = spotifyInteractor;
    }

    public void execute(String searchKey) throws IOException {
        SpotifyInputData spotifyInputData = new SpotifyInputData(searchKey);
        spotifyInteractor.execute(spotifyInputData);
    }
}
