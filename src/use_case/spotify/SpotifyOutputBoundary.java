package use_case.spotify;

/**
 * This class is the interface of the Spotify output boundary. It is implemented by SpotifyPresenter.
 */
public interface SpotifyOutputBoundary {

    /**
     * Called when Spotify runs successfully - prepares a success view.
     * @param spotifyOutputData is an SpotifyOutputData object
     */
    void prepareSuccessView(SpotifyOutputData spotifyOutputData);

    /**
     * Called when Spotify doesn't run successfully - prepares a fail view.
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
