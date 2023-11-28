package interface_adapter.play_game;

import entity.board.GameState;
import entity.user.User;

public class PlayGameState {
    private String errorMessage = "";
    private GameState currentGame = null;
    private User user = null;
    private int difficulty = 1;

    public PlayGameState() {}
    public void setCurrentGame(GameState gameState) { currentGame = gameState;}
    public void setNewGameError(String e) {errorMessage = e;}
    public void setUser(User user) {this.user = user;}
    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}
    public GameState getCurrentGame() {
        return currentGame;
    }
    public String getNewGameError() {return errorMessage;}
    public User getUser() {return user;}
    public int getDifficulty() {return difficulty;}
}
