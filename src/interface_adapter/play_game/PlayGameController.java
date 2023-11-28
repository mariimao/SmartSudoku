package interface_adapter.play_game;

import entity.board.GameState;
import entity.user.User;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInputData;
public class PlayGameController {
    final PlayGameInputBoundary playGameInteractor;
    public PlayGameController(PlayGameInputBoundary playGameInteractor) {this.playGameInteractor = playGameInteractor;}
    public void execute(User user, GameState currGame, int difficulty) {
        PlayGameInputData playGameInputData = new PlayGameInputData(user, currGame, difficulty);
        playGameInteractor.execute(playGameInputData);
    }
}
