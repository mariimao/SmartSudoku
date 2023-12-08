package use_case.play_music;

import java.io.IOException;

/**
 * Class representing the InputBoundary for the play music use case. This class is implemented by the PlayMusicInteractor.
 */
public interface PlayMusicInputBoundary {

    /**
     * Executes the play music use case.
     *
     * @param playMusicInputData is a PlayMusicInputData object
     */
    void execute(PlayMusicInputData playMusicInputData) throws IOException;
}
