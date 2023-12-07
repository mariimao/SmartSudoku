package use_case.play_music;

public interface PlayMusicOutputBoundary {
    void prepareSuccessView(PlayMusicOutputData playMusicOutputData);

    void prepareFailView(String error);
}
