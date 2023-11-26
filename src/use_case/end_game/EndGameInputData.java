package use_case.end_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class EndGameInputData {
    final private User user;
    final private GameState current_state;
    final private int time;
    final private int lives;


    public EndGameInputData(User user, GameState current_state, int time, int lives) {
        this.user = user;
        this.current_state = current_state;
        this.time = time;
        this.lives = lives;
    }

    String getUsername() {
        return user.getName();
    }

    GameState getCurrent_state() {
        return current_state;
    }

    int getTime() (return) // implement time

    int getLives() {
        return lives;
    }
}

// Need:
// user, final game state, time,
