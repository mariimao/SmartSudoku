package use_case.new_game;

import entity.board.GameState;
import entity.user.User;

public class NewGameInteractor implements NewGameInputBoundary{
    private final NewGameDataAccessInterface newGameDataAccessInterface;
    private final NewGameOutputBoundary newGamePresenter;

    public NewGameInteractor(NewGameDataAccessInterface newGameDataAccessInterface, NewGameOutputBoundary newGamePresenter) {
        this.newGameDataAccessInterface = newGameDataAccessInterface;
        this.newGamePresenter = newGamePresenter;
    }

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

