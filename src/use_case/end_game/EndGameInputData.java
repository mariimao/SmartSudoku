package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class EndGameInputData {
    final private String user;
    final private GameState current_state;
    final private int time;
    final private int lives;
    final private Scores scores;


    public EndGameInputData(String user, GameState current_state, int time, int lives, Scores scores) {
        this.user = user;
        this.current_state = current_state;
        this.time = time;
        this.lives = lives;
        this.scores = scores;
    }

    String getUsername() {
        return user;
    }

    GameState getCurrent_state() {
        return current_state;
    }

    boolean isCompleted() {
        return current_state.gameOver();
    }

    int spacesLeft() {
        return current_state.spacesLeft();
    }

    int getTime() {
        return this.time;
    }

    int getLives() {
        return this.lives;
    }
}

