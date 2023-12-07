package interface_adapter.pause_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.resume_game.ResumeGameState;
import interface_adapter.start.StartViewModel;
import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

/**
 * Class of Presenter for pause game use case. Implements PauseGameOutputBoundary
 */
public class PauseGamePresenter implements PauseGameOutputBoundary {
    private final PauseGameViewModel pauseGameViewModel;
    private ViewManagerModel viewManagerModel;

    public PauseGamePresenter(PauseGameViewModel pauseGameViewModel, ViewManagerModel viewManagerModel) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PauseGameOutputData pauseGameOutputData) {
        PauseGameState pauseGameState = pauseGameViewModel.getState();
        pauseGameState.setPausedGame(pauseGameOutputData.getCurrentGame());
        pauseGameState.setPastGames(pauseGameOutputData.getCurrentGame().getPastStates());
        pauseGameState.setUser(pauseGameOutputData.getUser());
        pauseGameState.setPastGames(pauseGameOutputData.getCurrentGame().getPastStates());
        this.viewManagerModel.setActiveViewName(pauseGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PauseGameState pauseGameState = pauseGameViewModel.getState();
        pauseGameState.setPauseGameError(error);
        pauseGameViewModel.firePropertyChanged();

    }
}
