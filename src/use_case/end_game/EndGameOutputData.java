package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

public class EndGameOutputData {
    private final String user;
    private final int score;

    public EndGameOutputData(String user, int score) {

        this.user = user;
        this.score = score;
    }
    public int getScore() {return score;}
    public String getUser() {return user;}
}
