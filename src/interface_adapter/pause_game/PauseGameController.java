package interface_adapter.pause_game;

import entity.board.GameState;
import use_case.pause_game.PauseGameInputBoundary;
import use_case.pause_game.PauseGameInputData;

import java.io.IOException;
import java.util.LinkedList;

/**
 * The PauseGameController class. Executes the interactor
 */
public class PauseGameController {
    final PauseGameInputBoundary userPauseGameInteractor;

    /**
     * Constructor for PauseGameController
     *
     * @param userPauseGameInteractor the interactor, is a PauseGameInputBoundary type
     */
    public PauseGameController(PauseGameInputBoundary userPauseGameInteractor) {
        this.userPauseGameInteractor = userPauseGameInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     *
     * @param userName   the username of the user
     * @param pausedGame the game they want to pause
     * @param pastGames  past games they've played
     * @throws IOException throws exception if username is incorrect
     */
    public void execute(String userName, GameState pausedGame, LinkedList<GameState> pastGames) throws IOException {
        PauseGameInputData pauseGameInputData = new PauseGameInputData(userName, pausedGame, pastGames);
        userPauseGameInteractor.execute(pauseGameInputData);
    }
}
