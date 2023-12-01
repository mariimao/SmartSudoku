package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class EndGameInputData {
    final private String username;
    final private GameState current_state;
    final private int time;
    final private int lives;
    final private Scores scores;


    public EndGameInputData(String username, GameState current_state, int time, int lives, Scores scores) {
        this.username = username;
        this.current_state = current_state;
        this.time = time;
        this.lives = lives;
        this.scores = scores;

    }

    String getUsername() {
        return username;
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

    void setScores() {scores.setScores(getTime(), spacesLeft(), getLives(), isCompleted());}

    Scores getScores() {return scores;}

}