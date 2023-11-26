package use_case.end_game;

import use_case.new_game.NewGameOutputData;

public interface EndGameOutputBoundary {

    void prepareSuccessView(EndGameOutputData endGameOutputData);
    void prepareFailView(String error);
}
}
