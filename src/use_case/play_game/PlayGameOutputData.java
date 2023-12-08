package use_case.play_game;

import entity.board.GameState;
import entity.user.User;
/**
 * Class representing the output data for play game.
 */
public class PlayGameOutputData {
    private final User user;
    private final GameState game;

    /**
     * Constructor for an PlayGameOutputData object.
     *
     * @param user is the current user
     * @param game is the current game state
     */

    public PlayGameOutputData(User user, GameState game) {

        this.user = user;
        this.game = game;
    }

    /**
     * @return the current game state
     */

    public GameState getGame() {
        return game;
    }

    /**
     * @return the user
     */

    public User getUser() {
        return user;
    }

}
