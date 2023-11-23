package interface_adapter.new_game;

import entity.user.User;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInputData;

public class NewGameController {
    final NewGameInputBoundary newGameInteractor;

    public NewGameController(NewGameInputBoundary newGameInteractor) {
        this.newGameInteractor = newGameInteractor;
    }
    public void execute(User user, int difficulty) {
        NewGameInputData newGameInputData = new NewGameInputData(user, difficulty);
        newGameInteractor.execute(newGameInputData);
    }
}