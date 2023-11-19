package use_case.pause_game;

import use_case.signup.SignupOutputData;

public interface PauseGameOutputBoundary {
    void prepareSuccessView(PauseGameOutputData pauseGameOutputData);

    void prepareFailView(String error);

}
