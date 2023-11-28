package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGamePresenter;
import interface_adapter.play_game.PlayGameViewModel;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInteractor;
import view.NewGameView;

public class NewGameUseCaseFactory {
    private NewGameUseCaseFactory() {}

    public static NewGameView create(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject, PlayGameViewModel playGameViewModel) {
        // TODO: Update these lines so that it includes the viewmodels that include the views for the games, leaderboard, etc.
        NewGameController newGameController = createUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
        PlayGameController playGameController = createUserPlayGameUseCase(viewManagerModel, playGameViewModel, userDataAccessObject);
        return new NewGameView(newGameViewModel, newGameController, playGameViewModel, playGameController);
    }

    private static NewGameController createUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }
    private static PlayGameController createUserPlayGameUseCase(ViewManagerModel viewManagerModel,
                                                                PlayGameViewModel playGameViewModel,
                                                                PlayGameDataAccessInterface playGameDataAccessInterface) {
        PlayGamePresenter playGamePresenter = new PlayGamePresenter(playGameViewModel, viewManagerModel);
        PlayGameInputBoundary playGameInteractor = new PlayGameInteractor(playGameDataAccessInterface, playGamePresenter);
        return new PlayGameController(playGameInteractor);
    }
}
