package interface_adapter.end_game;

import interface_adapter.ViewManagerModel;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;

/**
 * The End Game Presenter. Implements the End Game Output Boundary.
 */
public class EndGamePresenter implements EndGameOutputBoundary {
    private final EndGameViewModel endGameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Initializes the EndGamePresenter
     *
     * @param endGameViewModel the end game view model
     * @param viewManagerModel the view manager model
     */
    public EndGamePresenter(EndGameViewModel endGameViewModel, ViewManagerModel viewManagerModel) {
        this.endGameViewModel = endGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view
     *
     * @param endGameOutputData is an EndGameOutputData object
     */
    @Override
    public void prepareSuccessView(EndGameOutputData endGameOutputData) {
        EndGameState endGameState = endGameViewModel.getState();
        endGameState.setUser(endGameOutputData.getUser().getName());
        endGameState.setFinalGame(endGameOutputData.getFinalGame());
        endGameState.setScore(endGameOutputData.getScore());
        this.viewManagerModel.setActiveViewName(endGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view with a error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        EndGameState endGameState = endGameViewModel.getState();
        endGameState.setEndGameError(error);
        endGameViewModel.firePropertyChanged();

    }
}
