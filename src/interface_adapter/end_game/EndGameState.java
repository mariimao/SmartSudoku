package interface_adapter.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class EndGameState {
    private String errorMessage = "";
    private GameState finalGame = null;
    private User user = null;
    private Scores score = null;


    public EndGameState() {
    }

    public void setFinalGame(GameState gameState) {
        finalGame = gameState;
    }

    public void setEndGameError(String e) {
        errorMessage = e;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setScore(Scores score) {this.score = score;}

    public GameState getFinalGame() {
        return finalGame;
    }

    public String getEndGameError() {
        return errorMessage;
    }

    public Scores getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }
}
