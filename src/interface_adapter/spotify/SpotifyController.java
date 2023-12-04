package interface_adapter.spotify;

import use_case.spotify.SpotifyInputBoundary;
import use_case.spotify.SpotifyInputData;

import java.io.IOException;

/**
 * The class for SpotifyController. Acts as controller to send information to SpotifyInteractor.
 */
public class SpotifyController {
    final SpotifyInputBoundary spotifyInteractor;

    /**
     * Constructor of Spotify Controller
     * @param spotifyInteractor the interactor of spotify use case that makes decisions
     */
    public SpotifyController(SpotifyInputBoundary spotifyInteractor) {
        this.spotifyInteractor = spotifyInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     * @param searchKey the key that the user uses to search for songs
     * @throws IOException in the case that the search is not found or api call fails
     */
    public void execute(String searchKey) throws IOException {
        SpotifyInputData spotifyInputData = new SpotifyInputData(searchKey);
        spotifyInteractor.execute(spotifyInputData);
    }
}
