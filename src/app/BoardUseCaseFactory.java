package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGamePresenter;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGamePresenter;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.pause_game.PauseGameInteractor;
import use_case.user_move.UserMoveDataAccessInterface;
import use_case.user_move.UserMoveInteractor;
import view.BoardView;
import view.PausedGameView;

import javax.swing.*;
import java.io.IOException;

public class BoardUseCaseFactory {
    private BoardUseCaseFactory(){}

    public static BoardView create(ViewManagerModel viewManagerModel, EasyGameViewModel easyGameViewModel,
                                   PauseGameViewModel pauseGameViewModel, EndGameViewModel endGameViewModel,
                                   NewGameViewModel newGameViewModel, LeaderboardViewModel leaderboardViewModel,
                                   MenuViewModel menuViewModel, StartViewModel startViewModel,
                                   UserDAO userDataAccessObject) {

        EasyGameController easyGameController = createUserEasyGameUseCase(viewManagerModel, easyGameViewModel, userDataAccessObject);
        PauseGameController pauseGameController = createUserPauseUseCase(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel, userDataAccessObject);
        EndGameController endGameController = createUserEndGameUseCase(viewManagerModel, leaderboardViewModel, endGameViewModel, menuViewModel, userDataAccessObject);
        NewGameController newGameController = createUserNewGameUseCase(viewManagerModel, newGameViewModel, userDataAccessObject);

        return new BoardView(easyGameViewModel, easyGameController, pauseGameController, pauseGameViewModel, endGameController, endGameViewModel, newGameViewModel);


    }

    private static EndGameController createUserEndGameUseCase(ViewManagerModel viewManagerModel,
                                                              LeaderboardViewModel leaderboardViewModel,
                                                              EndGameViewModel endGameViewModel,
                                                              MenuViewModel menuViewModel,
                                                              EndGameDataAccessInterface endGameDataAccessInterface) {
        EndGamePresenter endGamePresenter = new EndGamePresenter(leaderboardViewModel, menuViewModel, endGameViewModel, viewManagerModel);
        EndGameInteractor endGameInteractor = new EndGameInteractor(endGameDataAccessInterface, endGamePresenter);
        return new EndGameController(endGameInteractor);
    }

    private static PauseGameController createUserPauseUseCase(StartViewModel startViewModel, MenuViewModel menuViewModel,
                                                              PauseGameViewModel pauseGameViewModel,
                                                              ViewManagerModel viewManagerModel, PauseGameDataAccessInterface pauseGameDataAccessInterface) {
        PauseGamePresenter pauseGamePresenter = new PauseGamePresenter(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel);
        PauseGameInteractor pauseGameInteractor = new PauseGameInteractor(pauseGameDataAccessInterface, pauseGamePresenter, pauseGameViewModel.getState().getUser());
        return new PauseGameController(pauseGameInteractor);
    }

    private static EasyGameController createUserEasyGameUseCase(ViewManagerModel viewManagerModel, EasyGameViewModel easyGameViewModel, UserMoveDataAccessInterface userMoveDataAccessInterface) {
        EasyGamePresenter easyGamePresenter = new EasyGamePresenter(viewManagerModel, easyGameViewModel);
        UserMoveInteractor userMoveInteractor = new UserMoveInteractor(userMoveDataAccessInterface, easyGamePresenter);
        return new EasyGameController(userMoveInteractor);
    }

    private static NewGameController createUserNewGameUseCase(ViewManagerModel viewManagerModel,
                                                           NewGameViewModel newGameViewModel,
                                                           NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }
}
