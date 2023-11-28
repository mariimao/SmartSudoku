package use_case.end_game;

import entity.user.User;

public class EndGameOutputData {
    private final User user;
    private final int score;

    public EndGameOutputData(User user, int score) {

        this.user = user;
        this.score = score;
    }
    public int getScore() {return score;}
    public User getUser() {return user;}
}
