package interface_adapter.resume_game;
import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class ResumeGameState {
    private String errorMessage = "";
    private GameState pausedGame = null;
    private LinkedList<GameState> pastGames = null;
    private String userName = "";

    public ResumeGameState() {}

    public void setPausedGame(GameState gameState) {pausedGame = gameState;}
    public void setPastGames(LinkedList<GameState> gameStateLinkedList) {pastGames = gameStateLinkedList;}
    public void setPauseGameError(String e) {errorMessage = e;}

    public void setUserName(String userName) {this.userName = userName;}

    public GameState getPausedGame() {
        return pausedGame;
    }

    public LinkedList<GameState> getPastGames() {
        return pastGames;
    }

    public String getPauseGameError() {return errorMessage;}
    public String getUserName() {return userName;}

}
