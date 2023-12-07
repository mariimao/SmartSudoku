package use_case.spotify;

import java.io.IOException;

/**
 * Class representing the InputBoundary for the Spotify usecase. This class is implemented by the SpotifyInteractor.
 */
public interface SpotifyInputBoundary {

    /**
     * Executes the Spotify use case.
     *
     * @param spotifyInputData is a SpotifyInputData object
     */
    void execute(SpotifyInputData spotifyInputData) throws IOException;
}
