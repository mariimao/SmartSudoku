package interface_adapter.new_game;

import entity.user.User;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInputData;

/**
 * The class for NewGameController. Acts as controller to send information to Interactor.
 */
public class NewGameController {
    final NewGameInputBoundary newGameInteractor;

    /**
     * Constructor for NewGameController
     * @param newGameInteractor     the interactor, is a NewGameInputBoundary type
     */
    public NewGameController(NewGameInputBoundary newGameInteractor) {this.newGameInteractor = newGameInteractor;}

    /**
     * Executes the use case's interactor to perform action
     * @param userName      the user's unique username
     * @param difficulty    the level of difficulty they chose, as an int
     */
    public void execute(String userName, int difficulty) {
        NewGameInputData newGameInputData = new NewGameInputData(userName, difficulty);
        newGameInteractor.execute(newGameInputData);
    }
}
