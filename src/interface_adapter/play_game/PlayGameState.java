package interface_adapter.play_game;

import entity.Scores;
import entity.board.GameState;

import java.time.LocalDateTime;

/**
 * The state of PlayGame ViewModel
 */
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

    private LocalDateTime startTime = null;

    private int timePlayed = 0;

    /**
     * Constructor for PlayGameState
     */
    public PlayGameState() {
    }

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

    public GameState getCurrentGame() {
        return currentGame;
    }

    //below are the getters and setters //
    public void setCurrentGame(GameState gameState) {
        currentGame = gameState;
    }

    public String getNewGameError() {
        return errorMessage;
    }

    public void setNewGameError(String e) {
        errorMessage = e;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSpacesLeft() {
        return spacesLeft;
    }

    public void setSpacesLeft(int spacesLeft) {
        this.spacesLeft = spacesLeft;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Scores getScores() {
        return this.scores;
    }

    public void setScores(Scores score) {
        scores = score;
    }


}
