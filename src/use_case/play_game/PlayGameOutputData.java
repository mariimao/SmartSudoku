package use_case.play_game;

import entity.board.GameState;
import entity.user.User;

public class PlayGameOutputData {
    private final User user;
    private final GameState game;

    public PlayGameOutputData(User user, GameState game) {

        this.user = user;
        this.game = game;
    }

    public GameState getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

}
