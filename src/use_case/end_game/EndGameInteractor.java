package use_case.end_game;

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
        //score
        EndGameOutputData endGameOutputData = new EndGameOutputData(user, score);
        endGamePresenter.prepareSuccessView(endGameOutputData);
    }
}
