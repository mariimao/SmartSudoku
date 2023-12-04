package interface_adapter.play_music;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameViewModel;
import use_case.play_music.PlayMusicOutputBoundary;
import use_case.play_music.PlayMusicOutputData;

import javax.swing.*;

public class PlayMusicPresenter implements PlayMusicOutputBoundary {

    private final NewGameViewModel newGameViewModel;

    private final ViewManagerModel viewManagerModel;

    public PlayMusicPresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PlayMusicOutputData playMusicOutputData) {
        String songName = playMusicOutputData.getSongName();
        JOptionPane.showMessageDialog(null,
                "You have successfully played the song: " + songName);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
}
