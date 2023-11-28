package interface_adapter.new_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.resume_game.ResumeGameState;
import use_case.new_game.NewGameOutputBoundary;
import use_case.new_game.NewGameOutputData;

import javax.swing.*;

public class NewGamePresenter implements NewGameOutputBoundary {
    private final NewGameViewModel newGameViewModel;
    private ViewManagerModel viewManagerModel;

    public NewGamePresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(NewGameOutputData newGameOutputData) {
        NewGameState newGameState = newGameViewModel.getState();
        newGameState.setGame(newGameOutputData.getNewGame());
        newGameState.setUser(newGameOutputData.getUser());
        this.viewManagerModel.setActiveViewName(newGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        // JOptionPane.showMessageDialog(null, "New Game Successfully Created");   // for now I'll just use a popup
    }

    @Override
    public void prepareFailView(String error) {
        NewGameState newGameState = newGameViewModel.getState();
        newGameState.setNewGameError(error);
        newGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);

    }
}
