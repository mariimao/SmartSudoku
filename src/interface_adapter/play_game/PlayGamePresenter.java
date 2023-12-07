package interface_adapter.play_game;

import interface_adapter.ViewManagerModel;
import use_case.play_game.PlayGameOutputBoundary;
import use_case.play_game.PlayGameOutputData;

import javax.swing.*;

/**
 * Class of Presenter for play game case. Implements PlayGameOutputBoundary
 */
public class PlayGamePresenter implements PlayGameOutputBoundary {
    private final PlayGameViewModel playGameViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for PlayGamePresenter
     * @param playGameViewModel the play game view model
     * @param viewManagerModel the view manager model
     */
    public PlayGamePresenter(PlayGameViewModel playGameViewModel, ViewManagerModel viewManagerModel) {
        this.playGameViewModel = playGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view
     * @param playGameOutputData
     */
    @Override
    public void prepareSuccessView(PlayGameOutputData playGameOutputData) {
        PlayGameState playGameState = playGameViewModel.getState();
        playGameState.setCurrentGame(playGameOutputData.getGame());
        playGameState.setDifficulty(playGameOutputData.getGame().getDifficulty());
        playGameState.setUserName(playGameOutputData.getUser().getName());
        playGameState.setLives(playGameOutputData.getGame().getLives());
        playGameState.setSpacesLeft(playGameOutputData.getGame().spacesLeft());
        playGameState.setIsCompleted(playGameOutputData.getGame().gameOver());
        playGameViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveViewName(playGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares fail view with error message
     * @param error
     */
    @Override
    public void prepareFailView(String error) {
        PlayGameState playGameState = playGameViewModel.getState();
        playGameState.setNewGameError(error);
        playGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);


    }
}
