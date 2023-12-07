package use_case.new_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Class representing the output data for NewGame.
 */
public class NewGameOutputData {
    private final User user;
    private final GameState newGame;

    /**
     * This is a constructor for a NewGameOutputData object.
     *
     * @param user    is a User object
     * @param newGame is a GameState object
     */
    public NewGameOutputData(User user, GameState newGame) {
        this.user = user;
        this.newGame = newGame;
    }

    /* ----- Getters and setters ----- */
    public GameState getNewGame() {
        return newGame;
    }

    public User getUser() {
        return user;
    }
}
