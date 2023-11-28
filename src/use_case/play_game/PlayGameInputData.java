package use_case.play_game;

import entity.board.GameState;
import entity.user.User;

public class PlayGameInputData {
    final private User user;
    final private GameState currentGame;
    final private int difficulty;

    public PlayGameInputData(User user, GameState currentGame, int difficulty) {
        this.user = user;
        this.currentGame = currentGame;
        this.difficulty = difficulty;
    }

    String getUsername() {
        if (user == null) {return null;}
        else {return user.getName();}
    }
    GameState getCurrentGame() {return currentGame;}
    // TODO: figure out how song timer will fit in to this use case

    int getDifficulty() {return difficulty;}
}
