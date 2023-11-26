package interface_adapter.play_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class PlayGameViewModel extends ViewModel {
    public PlayGameViewModel(String viewName) {
        super("PLAY GAME");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
