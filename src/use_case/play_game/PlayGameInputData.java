package use_case.play_game;

import entity.board.GameState;

public class PlayGameInputData {
    final private String userName;
    final private GameState currentGame;
    final private int difficulty;

    public PlayGameInputData(String userName, GameState currentGame, int difficulty) {
        this.userName = userName;
        this.currentGame = currentGame;
        this.difficulty = difficulty;
    }

    String getUsername() {
        return userName;
    }

    GameState getCurrentGame() {
        return currentGame;
    }
    // TODO: figure out how song timer will fit in to this use case

    int getDifficulty() {
        return difficulty;
    }
}
