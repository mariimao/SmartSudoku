package interface_adapter.end_game;

import entity.board.GameState;

/**
 * The state of EndGame ViewModel
 */
public class EndGameState {
    private String errorMessage = "";
    private GameState endGame = null;
    private String username = "";
    private int time = 0;
    private int lives = 0;
    private int score = 0;


    /**
     * Initializes an empty end game state object
     */
    public EndGameState() {
    }

    //Below are the Getters and Setter //
    public void setFinalGame(GameState gameState) {
        endGame = gameState;
    }

    public GameState getEndGame() {
        return endGame;
    }

    public String getEndGameError() {
        return errorMessage;
    }

    public void setEndGameError(String e) {
        errorMessage = e;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }
}
