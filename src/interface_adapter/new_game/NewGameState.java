package interface_adapter.new_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class NewGameState {
    private String errorMessage = "";
    private GameState game = null;
    private User user = null;
    private int difficulty = 1; // will make an easyboard automatically if not otherwise instructed

    public NewGameState() {}
    public void setGame(GameState gameState) { game = gameState;}
    public void setNewGameError(String e) {errorMessage = e;}
    public void setUser(User user) {this.user = user;}
    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}
    public GameState getGame() {
        return game;
    }
    public String getNewGameError() {return errorMessage;}
    public User getUser() {return user;}
    public int getDifficulty() {return difficulty;}
}
