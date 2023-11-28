package use_case.play_game;

public interface PlayGameOutputBoundary {
    void prepareSuccessView(PlayGameOutputData newGameOutputData);
    void prepareFailView(String error);
}
