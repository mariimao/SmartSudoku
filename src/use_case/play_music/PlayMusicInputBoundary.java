package use_case.play_music;

import java.io.IOException;

public interface PlayMusicInputBoundary {
    void execute(PlayMusicInputData playMusicInputData) throws IOException;
}
