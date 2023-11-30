package interface_adapter.play_game;

import entity.board.GameState;
import entity.user.User;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInputData;
public class PlayGameController {
    final PlayGameInputBoundary playGameInteractor;
    public PlayGameController(PlayGameInputBoundary playGameInteractor) {this.playGameInteractor = playGameInteractor;}

    public void execute(String username, GameState currentGame, int difficulty) {
        PlayGameInputData playGameInputData = new PlayGameInputData(username, currentGame, difficulty);
        playGameInteractor.execute(playGameInputData);
    }
}
