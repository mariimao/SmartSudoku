package interface_adapter.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;
import use_case.end_game.EndGameInputBoundary;
import use_case.end_game.EndGameInputData;

import java.util.LinkedList;

public class EndGameController {
    final EndGameInputBoundary endGameUseCaseInteractor;

    public EndGameController(EndGameInputBoundary endGameUseCaseInteractor) {
        this.endGameUseCaseInteractor = endGameUseCaseInteractor;
    }

    public void execute(String user, GameState current_state, int time, int lives, Scores scores) {
        EndGameInputData endGameInputData = new EndGameInputData(user, current_state, time, lives, scores);
        endGameUseCaseInteractor.execute(endGameInputData);
    }
}
