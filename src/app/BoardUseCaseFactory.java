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
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGamePresenter;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGamePresenter;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.pause_game.PauseGameInteractor;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInputBoundary;
import use_case.play_game.PlayGameInteractor;
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
                                   LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,
                                   StartViewModel startViewModel, PlayGameViewModel playGameViewModel,
                                   UserDAO userDataAccessObject) {

        EasyGameController easyGameController = createUserEasyGameUseCase(viewManagerModel, easyGameViewModel, userDataAccessObject);
        PauseGameController pauseGameController = createUserPauseUseCase(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel, userDataAccessObject);
        EndGameController endGameController = createUserEndGameUseCase(viewManagerModel, leaderboardViewModel, endGameViewModel, menuViewModel, userDataAccessObject);
        PlayGameController playGameController = createUserPlayGameUseCase(viewManagerModel, playGameViewModel, userDataAccessObject);

        return new BoardView(easyGameViewModel, easyGameController, pauseGameController, pauseGameViewModel, endGameController, endGameViewModel, playGameViewModel);


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
        PauseGameInteractor pauseGameInteractor = new PauseGameInteractor(pauseGameDataAccessInterface, pauseGamePresenter);
        return new PauseGameController(pauseGameInteractor);
    }

    private static EasyGameController createUserEasyGameUseCase(ViewManagerModel viewManagerModel, EasyGameViewModel easyGameViewModel, UserMoveDataAccessInterface userMoveDataAccessInterface) {
        EasyGamePresenter easyGamePresenter = new EasyGamePresenter(viewManagerModel, easyGameViewModel);
        UserMoveInteractor userMoveInteractor = new UserMoveInteractor(userMoveDataAccessInterface, easyGamePresenter);
        return new EasyGameController(userMoveInteractor);
    }

    private static PlayGameController createUserPlayGameUseCase(ViewManagerModel viewManagerModel,
                                                           PlayGameViewModel playGameViewModel,
                                                           PlayGameDataAccessInterface playGameDataAccessInterface) {
        PlayGamePresenter playGamePresenter = new PlayGamePresenter(playGameViewModel, viewManagerModel);
        PlayGameInputBoundary playGameInteractor = new PlayGameInteractor(playGameDataAccessInterface, playGamePresenter);
        return new PlayGameController(playGameInteractor);
    }
}
