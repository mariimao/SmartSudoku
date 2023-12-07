package interface_adapter.pause_game;

import interface_adapter.ViewManagerModel;
import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

/**
 * Class of Presenter for pause game use case. Implements PauseGameOutputBoundary
 */
public class PauseGamePresenter implements PauseGameOutputBoundary {
    private final PauseGameViewModel pauseGameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for PauseGamePresenter
     *
     * @param pauseGameViewModel the pause game view model
     * @param viewManagerModel   the view manager model
     */
    public PauseGamePresenter(PauseGameViewModel pauseGameViewModel, ViewManagerModel viewManagerModel) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view
     *
     * @param pauseGameOutputData is an PauseGameOutputData object
     */
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

    /**
     * Prepares fail view with error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        PauseGameState pauseGameState = pauseGameViewModel.getState();
        pauseGameState.setPauseGameError(error);
        pauseGameViewModel.firePropertyChanged();

    }
}
