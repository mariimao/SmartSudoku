package use_case.new_game;

import entity.board.GameState;
import entity.user.User;

/**
 * Class representing the interactor for the NewGame usecase. This class implements the NewGameInputBoundary.
 */
public class NewGameInteractor implements NewGameInputBoundary {
    private final NewGameDataAccessInterface newGameDataAccessInterface;
    private final NewGameOutputBoundary newGamePresenter;

    /**
     * Constructor for a NewGameInteractor object.
     * @param newGameDataAccessInterface is a NewGameDataAccessInterface object.
     * @param newGamePresenter is a NewGameOutputBoundary object.
     */
    public NewGameInteractor(NewGameDataAccessInterface newGameDataAccessInterface, NewGameOutputBoundary newGamePresenter) {
        this.newGameDataAccessInterface = newGameDataAccessInterface;
        this.newGamePresenter = newGamePresenter;
    }

    /**
     * Executes the NewGame use case.
     * If the user is null, the function will throw an error. Otherwise, it will make a new GameState object with the
     * corresponding difficulty, and send the information to the presenter.
     * @param newGameInputData is an NewGameInputData object
     */
    @Override
    public void execute(NewGameInputData newGameInputData) {
        User user = newGameDataAccessInterface.get(newGameInputData.getUsername());
        if (user == null) {
            newGamePresenter.prepareFailView("Error: No User is Logged In");
        }
        else {
            GameState newGame = new GameState(newGameInputData.getDifficulty());
            NewGameOutputData newGameOutputData = new NewGameOutputData(user, newGame);
            newGamePresenter.prepareSuccessView(newGameOutputData);
        }
    }
}

