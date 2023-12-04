package interface_adapter.end_game;

import entity.Scores;
import entity.board.GameState;
import use_case.end_game.EndGameInputBoundary;
import use_case.end_game.EndGameInputData;

public class EndGameController {
    final EndGameInputBoundary endGameUseCaseInteractor;

    public EndGameController(EndGameInputBoundary endGameUseCaseInteractor) {
        this.endGameUseCaseInteractor = endGameUseCaseInteractor;
    }

    public void execute(String user, GameState current_state, int time, int lives) {
        EndGameInputData endGameInputData = new EndGameInputData(user, current_state, time, lives);
        endGameUseCaseInteractor.execute(endGameInputData);
    }
}
