package interface_adapter.pause_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

/**
 * The state of PauseGame ViewModel
 */
public class PauseGameState {
    private String errorMessage = "";
    private GameState pausedGame = null;
    private LinkedList<GameState> pastGames = null;
    private User user = null;

    /**
     * Constructor for PauseGameState
     */
    public PauseGameState() {
    }

    public GameState getPausedGame() {
        return pausedGame;
    }

    //Below are the getters and setters
    public void setPausedGame(GameState gameState) {
        pausedGame = gameState;
    }

    public LinkedList<GameState> getPastGames() {
        return pastGames;
    }

    public void setPastGames(LinkedList<GameState> gameStateLinkedList) {
        pastGames = gameStateLinkedList;
    }

    public String getPauseGameError() {
        return errorMessage;
    }

    public void setPauseGameError(String e) {
        errorMessage = e;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
