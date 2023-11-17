package interface_adapter.pause_game;

import entity.GameState;
import entity.User;
import use_case.pause_game.PauseGameInputBoundary;
import use_case.pause_game.PauseGameInputData;

import java.util.LinkedList;

public class PauseGameController {
    final PauseGameInputBoundary userPauseGameInteractor;

    public PauseGameController(PauseGameInputBoundary userPauseGameInteractor) {
        this.userPauseGameInteractor = userPauseGameInteractor;
    }

    public void execute(User user, GameState pausedGame, LinkedList<GameState> pastGames) {
        PauseGameInputData pauseGameInputData = new PauseGameInputData(user, pausedGame, pastGames);
        userPauseGameInteractor.execute(pauseGameInputData);
    }
}
