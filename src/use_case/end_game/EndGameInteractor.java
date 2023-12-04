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
        User user = endGameDataAccessInterface.get(endGameInputData.getUsername());
        boolean isCompleted = endGameInputData.isCompleted();
        int spacesLeft = endGameInputData.spacesLeft();
        int lives = endGameInputData.getLives();
        int timePlayed = endGameInputData.getTime();
        Scores score = new Scores();
        score.setScores(timePlayed, spacesLeft, lives, isCompleted);
        endGameDataAccessInterface.addScore(user, LocalTime.from(LocalDateTime.now()), (Integer) timePlayed);
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score.getScores(), endGameInputData.getCurrent_state());
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
