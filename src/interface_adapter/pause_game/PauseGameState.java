package interface_adapter.pause_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class PauseGameState {
    private String errorMessage = "";
    private GameState pausedGame = null;
    private LinkedList<GameState> pastGames = null;
    private User user = null;
    private int pausedTime = 1000;


    public PauseGameState() {}

    public void setPausedGame(GameState gameState) {pausedGame = gameState;}
    public void setPastGames(LinkedList<GameState> gameStateLinkedList) {pastGames = gameStateLinkedList;}
    public void setPauseGameError(String e) {errorMessage = e;}
    public void setUser(User user) {this.user = user;}

    public GameState getPausedGame() {
        return pausedGame;
    }

    public LinkedList<GameState> getPastGames() {
        return pastGames;
    }

    public String getPauseGameError() {return errorMessage;}
    public User getUser() {return user;}

    public int getPausedTime() {
        return pausedTime;
    }

    public void setPausedTime(int pausedTime) {
        this.pausedTime = pausedTime;
    }
}
