package use_case.new_game;

import use_case.resume_game.ResumeGameOutputData;

public interface NewGameOutputBoundary {
    void prepareSuccessView(NewGameOutputData newGameOutputData);
    void prepareFailView(String error);
}
