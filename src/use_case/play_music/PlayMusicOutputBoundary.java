package use_case.play_music;

/**
 * This class is the interface of the play music output boundary. It is implemented by PlayMusicPresenter.
 */
public interface PlayMusicOutputBoundary {

    /**
     * Called when play music runs successfully - prepares a success view.
     *
     * @param playMusicOutputData is an PlayMusicOutputData object
     */
    void prepareSuccessView(PlayMusicOutputData playMusicOutputData);

    /**
     * Called when play music doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */

    void prepareFailView(String error);
}
