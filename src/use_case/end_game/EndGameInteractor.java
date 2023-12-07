package use_case.end_game;

import entity.Scores;
import entity.user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class representing the interactor for the EndGame usecase. This class implements the EndGameInputBoundary.
 */
public class EndGameInteractor implements EndGameInputBoundary {
    final EndGameDataAccessInterface endGameDataAccessInterface;
    final EndGameOutputBoundary endGamePresenter;

    /**
     * Constructor for the EndGameInteractor object.
     *
     * @param endGameDataAccessInterface is a EndGameDataAccessInterface object
     * @param endGamePresenter           is a EndGameOutputBoundary object
     */
    public EndGameInteractor(EndGameDataAccessInterface endGameDataAccessInterface, EndGameOutputBoundary endGamePresenter) {
        this.endGameDataAccessInterface = endGameDataAccessInterface;
        this.endGamePresenter = endGamePresenter;
    }

    /**
     * Executes the EndGame UseCase.
     * This function creates a new Scores object and adds the score to the database for the respective user. It stores the
     * user, scores, and the state of the game into an endGameOutputData object, and sends this to the endGamePresenters
     *
     * @param endGameInputData is an EndGameInputData object
     */
    @Override
    public void execute(EndGameInputData endGameInputData) {
        User user = endGameDataAccessInterface.get(endGameInputData.getUsername());
        boolean isCompleted = endGameInputData.isCompleted();
        int spacesLeft = endGameInputData.spacesLeft();
        int lives = endGameInputData.getLives();
        int timePlayed = endGameInputData.getTime();
        Scores score = new Scores();
        score.setScores(timePlayed, spacesLeft, lives, isCompleted);
        endGameDataAccessInterface.addScore(user, LocalTime.from(LocalDateTime.now()), timePlayed);
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score.getScores(), endGameInputData.getCurrent_state());
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
