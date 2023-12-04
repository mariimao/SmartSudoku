package use_case.play_music;

import interface_adapter.play_music.PlayMusicController;

import java.io.IOException;

public interface PlayMusicInputBoundary {
    void execute(PlayMusicInputData playMusicInputData) throws IOException;
}
