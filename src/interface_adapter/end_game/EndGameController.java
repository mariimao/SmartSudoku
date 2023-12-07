package interface_adapter.end_game;

import entity.Scores;
import entity.board.GameState;
import use_case.end_game.EndGameInputBoundary;
import use_case.end_game.EndGameInputData;

/**
 * The controller for the end game.
 */
public class EndGameController {
    final EndGameInputBoundary endGameUseCaseInteractor;

    /**
     * Initializes a new EndGame Controller.
     *
     * @param endGameUseCaseInteractor the use case interactor for endgame
     */
    public EndGameController(EndGameInputBoundary endGameUseCaseInteractor) {
        this.endGameUseCaseInteractor = endGameUseCaseInteractor;
    }

    /**
     * Creates the end game input data and calls the interactor.
     *
     * @param user          the current user's name, is a String
     * @param current_state the current state, is a GameState object
     * @param time          the time they spent playing, is int
     * @param lives         the lives they have left, is int
     * @param scores        their calculated score, is a Score object
     */
    public void execute(String user, GameState current_state, int time, int lives, Scores scores) {
        EndGameInputData endGameInputData = new EndGameInputData(user, current_state, time, lives, scores);
        endGameUseCaseInteractor.execute(endGameInputData);
    }
}
