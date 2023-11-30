package use_case.spotify;

public interface SpotifyOutputBoundary {
    void prepareSuccessView(SpotifyOutputData spotifyOutputData);

    void prepareFailView(String error);
}
