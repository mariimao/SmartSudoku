package use_case.end_game;

import use_case.new_game.NewGameInputData;

public interface EndGameInputBoundary {
    void execute(EndGameInputData endGameInputData);
}
