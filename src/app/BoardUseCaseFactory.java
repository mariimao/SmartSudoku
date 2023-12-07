package app;

import data_access.SpotifyDAO;
import data_access.SudokuDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.make_move.MakeMoveController;
import interface_adapter.make_move.MakeMovePresenter;
import interface_adapter.make_move.MakeMoveViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGamePresenter;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.make_move.MakeMoveBoardDataAccessInterface;
import use_case.make_move.MakeMoveDataAccessInterface;
import use_case.make_move.MakeMoveInteractor;
import use_case.pause_game.PauseGameDataAccessInterface;
import use_case.pause_game.PauseGameInteractor;
import use_case.play_music.PlayMusicDataAccessInterface;
import view.BoardView;

/**
 * Use case factory for the Board state.
 * This class creates the data for the views in a BoardView object, which are the pause game state,
 * end game state, play game state, and make move state.
 */
public class BoardUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private BoardUseCaseFactory() {
    }

    /**
     * Creates a new BoardView object.
     *
     * @param viewManagerModel     is a new ViewManagerModel object
     * @param pauseGameViewModel   is a new PauseGameViewModel object
     * @param endGameViewModel     is a new EndGameViewModel object
     * @param leaderboardViewModel is a new LeaderboardViewModel object
     * @param menuViewModel        is a new MenuViewModel object
     * @param startViewModel       is a new StartViewModel object
     * @param playGameViewModel    is a new PlayGameViewModel object
     * @param userDataAccessObject is a new UserDataAccessObject
     * @return BoardView object, with parameters for newly created relevant models and controllers
     */
    public static BoardView create(ViewManagerModel viewManagerModel,
                                   PauseGameViewModel pauseGameViewModel, EndGameViewModel endGameViewModel,
                                   LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,
                                   StartViewModel startViewModel, PlayGameViewModel playGameViewModel, MakeMoveViewModel
                                           makeMoveViewModel,
                                   UserDAO userDataAccessObject, SudokuDAO boardDataAccessObject,
                                   SpotifyDAO spotifyDAO) {

        PauseGameController pauseGameController = createUserPauseUseCase(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel, userDataAccessObject, spotifyDAO);
        EndGameController endGameController = createUserEndGameUseCase(viewManagerModel, leaderboardViewModel, endGameViewModel, menuViewModel, userDataAccessObject);
        MakeMoveController makeMoveController = createUserMakeMoveUseCase(userDataAccessObject, makeMoveViewModel, viewManagerModel, boardDataAccessObject);

        return new BoardView(pauseGameController, pauseGameViewModel, endGameController, endGameViewModel, playGameViewModel,
                makeMoveController);


    }

    /**
     * Helper function for the BoardView constructor. Creates a new MakeMoveController object.
     *
     * @param viewManagerModel           is a ViewManagerModel object
     * @param leaderboardViewModel       is a LeaderboardViewModel object
     * @param endGameViewModel           is an EndGameViewModel object
     * @param menuViewModel              is a MenuViewModel object
     * @param endGameDataAccessInterface is an EndGameDataAccessInterface object
     * @return an EndGameController object, to be passed into the constructor
     */
    private static EndGameController createUserEndGameUseCase(ViewManagerModel viewManagerModel,
                                                              LeaderboardViewModel leaderboardViewModel,
                                                              EndGameViewModel endGameViewModel,
                                                              MenuViewModel menuViewModel,
                                                              EndGameDataAccessInterface endGameDataAccessInterface) {
        EndGamePresenter endGamePresenter = new EndGamePresenter(endGameViewModel, viewManagerModel);
        EndGameInteractor endGameInteractor = new EndGameInteractor(endGameDataAccessInterface, endGamePresenter);
        return new EndGameController(endGameInteractor);
    }

    /**
     * Helper function for the BoardView constructor. Creates a new PauseGameController object.
     *
     * @param startViewModel               is a StartViewModel object
     * @param menuViewModel                is a MenuViewModel object
     * @param pauseGameViewModel           is a PauseGameViewModel object
     * @param viewManagerModel             is a ViewManagerModel object
     * @param pauseGameDataAccessInterface is a PauseGameDataAccessInterface object
     * @return a PauseGameController object, to be passed into the constructor
     */
    private static PauseGameController createUserPauseUseCase(StartViewModel startViewModel, MenuViewModel menuViewModel,
                                                              PauseGameViewModel pauseGameViewModel,
                                                              ViewManagerModel viewManagerModel, PauseGameDataAccessInterface pauseGameDataAccessInterface,
                                                              PlayMusicDataAccessInterface playMusicDataAccessInterface) {
        PauseGamePresenter pauseGamePresenter = new PauseGamePresenter(pauseGameViewModel, viewManagerModel);
        PauseGameInteractor pauseGameInteractor = new PauseGameInteractor(pauseGameDataAccessInterface, playMusicDataAccessInterface, pauseGamePresenter);
        return new PauseGameController(pauseGameInteractor);
    }

    /**
     * Creates a MakeMoveController
     *
     * @param makeMoveDataAccessInterface is a MakeMoveDataAccessInterface object
     * @param makeMoveViewModel           is a MakeMoveViewModel object
     * @param viewManagerModel            is a ViewManagerModel object
     * @return a MakeMoveController
     */
    private static MakeMoveController createUserMakeMoveUseCase(MakeMoveDataAccessInterface makeMoveDataAccessInterface,
                                                                MakeMoveViewModel makeMoveViewModel,
                                                                ViewManagerModel viewManagerModel,
                                                                MakeMoveBoardDataAccessInterface makeMoveBoardDataAccessInterface) {
        MakeMovePresenter makeMovePresenter = new MakeMovePresenter(makeMoveViewModel, viewManagerModel);
        MakeMoveInteractor makeMoveInteractor = new MakeMoveInteractor(makeMoveDataAccessInterface, makeMovePresenter, makeMoveBoardDataAccessInterface);
        return new MakeMoveController(makeMoveInteractor);
    }
}
