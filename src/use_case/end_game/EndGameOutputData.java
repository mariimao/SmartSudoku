package use_case.end_game;

import entity.Scores;
import entity.board.GameState;
import entity.user.User;

public class EndGameOutputData {
    private final User user;
    private final Scores score;
    private boolean useCaseFailed;

    public EndGameOutputData(User user, Scores score, boolean useCaseFailed) {

        this.user = user;
        this.score = score;
        this.useCaseFailed = useCaseFailed;
    }


    public Scores getScore() {return score;}
    public User getUser() {return user;}
    public GameState getFinalGame() {return user.getFinalGame();}
    public boolean isUseCaseFailed() {return useCaseFailed;}
}
