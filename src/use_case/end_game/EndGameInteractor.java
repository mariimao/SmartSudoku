package use_case.end_game;

import entity.Scores;
import entity.Scores;
import entity.board.GameState;
import entity.user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EndGameInteractor implements EndGameInputBoundary {
    final EndGameDataAccessInterface endGameDataAccessInterface;
    final EndGameOutputBoundary endGamePresenter;

    public EndGameInteractor(EndGameDataAccessInterface endGameDataAccessInterface, EndGameOutputBoundary endGamePresenter) {
        this.endGameDataAccessInterface = endGameDataAccessInterface;
        this.endGamePresenter = endGamePresenter;
    }

    @Override
    public void execute(EndGameInputData endGameInputData) {
        String user = endGameInputData.getUsername();
        boolean completed = true; // why not
        int lives = endGameInputData.getLives();
        int timePlayed = endGameInputData.getTime();
        Scores score = new Scores();
        score.setScores(timePlayed, 0, lives, completed); //no deduct on boards
        endGameDataAccessInterface.addScore(endGameDataAccessInterface.get(user), LocalTime.from(LocalDateTime.now()), (Integer) timePlayed);

        // akunna: i had to make score point to something and also change the user name into a user
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score.getScores());
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
