package interface_adapter.end_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class EndGameState {
    private String errorMessage = "";
    private GameState endGame = null;
    private User user = null;
    private int time = 0;
    private int lives = 0;
    private int score = 0;


    public EndGameState() {
    }

    public void setEndGame(GameState gameState) {
        endGame = gameState;
    }

    public void setEndGameError(String e) {
        errorMessage = e;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScore(int score) {this.score = score;}

    public void setLives(int lives) {this.lives = lives;}

    public GameState getEndGame() {
        return endGame;
    }

    public String getEndGameError() {
        return errorMessage;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {return lives;}

    public User getUser() {
        return user;
    }
}
