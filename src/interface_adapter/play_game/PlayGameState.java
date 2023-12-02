package interface_adapter.play_game;

import entity.board.GameState;
import entity.user.User;

import java.time.LocalDateTime;

public class PlayGameState {
    private String errorMessage = "";
    private GameState currentGame = null;
    private String userName = null;
    private int difficulty = 1;

    private LocalDateTime startTime = null;

    private int timePlayed = 0;

    public PlayGameState() {}
    public void setCurrentGame(GameState gameState) { currentGame = gameState;}
    public void setNewGameError(String e) {errorMessage = e;}

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}
    public GameState getCurrentGame() {
        return currentGame;
    }
    public String getNewGameError() {return errorMessage;}
    public int getDifficulty() {return difficulty;}

    public String  getUserName() {return this.userName;}

    public void setUserName(String username) {this.userName = username;}
}
