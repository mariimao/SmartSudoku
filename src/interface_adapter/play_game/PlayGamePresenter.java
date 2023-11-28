package interface_adapter.play_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameState;
import use_case.play_game.PlayGameOutputBoundary;
import use_case.play_game.PlayGameOutputData;

import javax.swing.*;

public class PlayGamePresenter implements PlayGameOutputBoundary {
    private final PlayGameViewModel playGameViewModel;
    private ViewManagerModel viewManagerModel;

    public PlayGamePresenter(PlayGameViewModel playGameViewModel, ViewManagerModel viewManagerModel) {
        this.playGameViewModel = playGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PlayGameOutputData playGameOutputData) {
        PlayGameState playGameState = playGameViewModel.getState();
        playGameState.setCurrentGame(playGameOutputData.getGame());
        playGameState.setDifficulty(playGameOutputData.getGame().getDifficulty());
        playGameState.setUser(playGameOutputData.getUser());
        playGameViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveViewName(playGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        PlayGameState playGameState = playGameViewModel.getState();
        playGameState.setNewGameError(error);
        playGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);


    }
}
