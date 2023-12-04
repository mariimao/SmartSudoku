package use_case.spotify;

import java.io.IOException;

public interface SpotifyInputBoundary {
    void execute(SpotifyInputData spotifyInputData) throws IOException;
}
