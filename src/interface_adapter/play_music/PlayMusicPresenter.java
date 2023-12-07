package interface_adapter.play_music;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameViewModel;
import use_case.play_music.PlayMusicOutputBoundary;
import use_case.play_music.PlayMusicOutputData;

import javax.swing.*;

/**
 * Class of Presenter for play music case. Implements PlayMusicOutputBoundary
 */
public class PlayMusicPresenter implements PlayMusicOutputBoundary {

    private final NewGameViewModel newGameViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for PlayMusicPresenter
     *
     * @param newGameViewModel the new game view model
     * @param viewManagerModel the view manager
     */
    public PlayMusicPresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view
     *
     * @param playMusicOutputData is a PlayMusicOutputData object
     */
    @Override
    public void prepareSuccessView(PlayMusicOutputData playMusicOutputData) {
        String songName = playMusicOutputData.getSongName();
        JOptionPane.showMessageDialog(null,
                "You have successfully played the song: " + songName);
    }

    /**
     * Prepares fail view with error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
}
