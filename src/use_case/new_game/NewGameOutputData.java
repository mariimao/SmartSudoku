package use_case.new_game;

import entity.board.GameState;
import entity.user.User;

public class NewGameOutputData {
    private final User user;
    private GameState newGame;

    public NewGameOutputData(User user, GameState newGame) {

        this.user = user;
        this.newGame = newGame;
    }
    public GameState getNewGame() {return newGame;}
    public User getUser() {return user;}
}
