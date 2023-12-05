package use_case.play_music;

public interface PlayMusicOutputBoundary {
    public void prepareSuccessView(PlayMusicOutputData playMusicOutputData);

    public void prepareFailView(String error);
}
