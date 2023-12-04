package interface_adapter.end_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuViewModel;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;

import javax.swing.*;

/**
 * Class representing the EndGamePresenter.
 * This class is responsible for updating the views.
 */
public class EndGamePresenter implements EndGameOutputBoundary {
    private final MenuViewModel menuViewModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final EndGameViewModel endGameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for an EndGamePresenter object.
     * @param leaderboardViewModel is a LeaderboardViewModel object
     * @param menuViewModel is a MenuViewModel object
     * @param endGameViewModel is an EndGameViewModel object
     * @param viewManagerModel is a ViewManagerModel object
     */
    public EndGamePresenter(LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,
                            EndGameViewModel endGameViewModel, ViewManagerModel viewManagerModel) {
            this.leaderboardViewModel = leaderboardViewModel;
            this.menuViewModel = menuViewModel;
            this.endGameViewModel = endGameViewModel;
            this.viewManagerModel = viewManagerModel;
        }

    /**
     * Called when EndGame runs successfully - prepares a success view by notifying the viewManagerModel.
     * @param endGameOutputData is an EndGameOutputData object
     */
    @Override
        public void prepareSuccessView(EndGameOutputData endGameOutputData) {
        viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        }

    /**
     * Called when EndGame doesn't run successfully - prepares a fail view. Notifies the endGameViewModel properly.
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        EndGameState endGameState = endGameViewModel.getState();
        endGameState.setEndGameError(error);
        endGameViewModel.firePropertyChanged();
    }
}
