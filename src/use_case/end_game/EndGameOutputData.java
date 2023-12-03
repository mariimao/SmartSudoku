package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

public class EndGameOutputData {
    private final String user;
    private final int score;
    private final GameState finalGame;

    public EndGameOutputData(String user, int score, GameState finalGame) {
        this.user = user;
        this.score = score;
        this.finalGame = finalGame;
    }
    public int getScore() {return score;}
    public String getUser() {return user;}

    public GameState getFinalGame() {
        return finalGame;
    }
}
