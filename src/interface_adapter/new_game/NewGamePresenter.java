package interface_adapter.new_game;

import interface_adapter.ViewManagerModel;
import use_case.new_game.NewGameOutputBoundary;
import use_case.new_game.NewGameOutputData;

import javax.swing.*;

/**
 * Class of Presenter for new game case. Implements NewGameOutputBoundary
 */
public class NewGamePresenter implements NewGameOutputBoundary {
    private final NewGameViewModel newGameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for NewGamePresenter
     *
     * @param newGameViewModel the new game view model
     * @param viewManagerModel the view manager model
     */
    public NewGamePresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view
     *
     * @param newGameOutputData is an NewGameOutputData object
     */
    @Override
    public void prepareSuccessView(NewGameOutputData newGameOutputData) {
        NewGameState newGameState = newGameViewModel.getState();
        newGameState.setGame(newGameOutputData.getNewGame());
        newGameState.setUser(newGameOutputData.getUser());
        this.viewManagerModel.setActiveViewName(newGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares fail view with error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        NewGameState newGameState = newGameViewModel.getState();
        newGameState.setNewGameError(error);
        newGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);

    }
}
