package interface_adapter.pause_game;

import entity.board.GameState;
import entity.user.User;
import use_case.pause_game.PauseGameInputBoundary;
import use_case.pause_game.PauseGameInputData;

import java.io.IOException;
import java.util.LinkedList;

public class PauseGameController {
    final PauseGameInputBoundary userPauseGameInteractor;

    public PauseGameController(PauseGameInputBoundary userPauseGameInteractor) {
        this.userPauseGameInteractor = userPauseGameInteractor;
    }

    public void execute(String userName, GameState pausedGame, LinkedList<GameState> pastGames) throws IOException {
        PauseGameInputData pauseGameInputData = new PauseGameInputData(userName, pausedGame, pastGames);
        userPauseGameInteractor.execute(pauseGameInputData);
    }
}
