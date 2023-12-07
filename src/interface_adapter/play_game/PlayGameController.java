package interface_adapter.play_game;

import entity.board.GameState;
import entity.user.User;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInputData;

/**
 * The class for PlayGameController. Acts as controller to send information to PlayGameInteractor.
 */
public class PlayGameController {
    final PlayGameInputBoundary playGameInteractor;

    /**
     * Constructor of PlayGameController
     * @param playGameInteractor the interactor that makes decisions with input data
     */
    public PlayGameController(PlayGameInputBoundary playGameInteractor) {this.playGameInteractor = playGameInteractor;}

    /**
     * Executes the use case's interactor to perform action
     * @param username          the username of the player
     * @param currentGame       the current game
     * @param difficulty        the selected difficultu
     */
    public void execute(String username, GameState currentGame, int difficulty) {
        PlayGameInputData playGameInputData = new PlayGameInputData(username, currentGame, difficulty);
        playGameInteractor.execute(playGameInputData);
    }
}
