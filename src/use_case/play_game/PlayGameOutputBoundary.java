package use_case.play_game;

/**
 * This class is the interface of the play game output boundary. It is implemented by PlayGamePresenter.
 */
public interface PlayGameOutputBoundary {

    /**
     * Called when play game runs successfully - prepares a success view.
     *
     * @param newGameOutputData is an PlayMusicOutputData object
     */
    void prepareSuccessView(PlayGameOutputData newGameOutputData);

    /**
     * Called when play game doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
