package use_case.end_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Class representing the output data for EndGame.
 */
public class EndGameOutputData {
    private final User user;
    private final int score;
    private final GameState finalGame;

    /**
     * Constructor for an EndGameOutputData object.
     *
     * @param user      is a User object
     * @param score     is an int representing the current score
     * @param finalGame is a GameState object of the final game state
     */
    public EndGameOutputData(User user, int score, GameState finalGame) {

        this.user = user;
        this.score = score;
        this.finalGame = finalGame;
    }

    /* ----- Getters and setters ----- */
    public int getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }

    public GameState getFinalGame() {
        return finalGame;
    }
}