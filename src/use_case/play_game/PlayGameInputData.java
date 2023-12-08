package use_case.play_game;

import entity.board.GameState;

/**
 * Class representing the input data of the play game use case
 */

public class PlayGameInputData {
    final private String userName;
    final private GameState currentGame;
    final private int difficulty;

    /**
     * Constructor for the PlayGameInputData object.
     *
     * @param userName the user's username
     * @param currentGame the current game state
     * @param difficulty the game difficulty
     */

    public PlayGameInputData(String userName, GameState currentGame, int difficulty) {
        this.userName = userName;
        this.currentGame = currentGame;
        this.difficulty = difficulty;
    }

    /**
     * @return the user's username
     */
    String getUsername() {
        return userName;
    }

    /**
     * @return the current game state
     */

    GameState getCurrentGame() {
        return currentGame;
    }

    /**
     * @return the game difficulty
     */

    int getDifficulty() {
        return difficulty;
    }
}
