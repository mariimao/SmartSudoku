package use_case.end_game;

import entity.board.GameState;

/**
 * Class representing the input data of the EndGame.
 */
public class EndGameInputData {
    final private String user;
    final private GameState current_state;
    final private int time;
    final private int lives;

    /**
     * Constructor for the EndGameInputData object.
     * @param user is a String object representing the username
     * @param current_state is a GameState object representing the current state
     * @param time is an int representing the current time elapsed
     * @param lives is an int representing the current lives left
     */
    public EndGameInputData(String user, GameState current_state, int time, int lives) {
        this.user = user;
        this.current_state = current_state;
        this.time = time;
        this.lives = lives;
    }

    /* ----- Getters and setters ----- */
    public String getUsername() {
        return user;
    }

    public GameState getCurrent_state() {
        return current_state;
    }

    public boolean isCompleted() {
        return current_state.gameOver();
    }

    public int spacesLeft() {
        return current_state.spacesLeft();
    }

    public int getTime() {
        return this.time;
    }

    public int getLives() {
        return this.lives;
    }
}

