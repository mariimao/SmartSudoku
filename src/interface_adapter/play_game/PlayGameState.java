package interface_adapter.play_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

public class PlayGameState {
    private String errorMessage = "";
    private GameState currentGame = null;
    private String userName = null;
    private int difficulty = 1;
    private int time = 0;
    private int lives = 0;
    private Scores scores = null;
    private int spacesLeft = 0;
    private boolean isCompleted = false;

    public PlayGameState() {}
    public void setCurrentGame(GameState gameState) { currentGame = gameState;}
    public void setNewGameError(String e) {errorMessage = e;}
    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}
    public GameState getCurrentGame() {
        return currentGame;
    }
    public String getNewGameError() {return errorMessage;}
    public int getDifficulty() {return difficulty;}

    public String  getUserName() {return this.userName;}

    public void setUserName(String username) {this.userName = username;}

    public void setTime(int time) {this.time = time;}

    public int getTime() {return this.time;}

    public void setLives(int lives) {this.lives = lives;}
    public void setSpacesLeft(int spacesLeft) {this.spacesLeft = spacesLeft;}

    public int getSpacesLeft() {
        return spacesLeft;
    }

    public void setIsCompleted(boolean isCompleted) {this.isCompleted = isCompleted;}

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getLives() {return lives;}

    public void setScores (Scores score) {scores = score;}

    public Scores getScores() {return this.scores;}


}
