package interface_adapter.play_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for play game use case. Extends ViewModel Class.
 */
public class PlayGameViewModel extends ViewModel {
    public static final String FAIL_MESSAGE = "Unable to Continue PLaying Game. Try Again";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final PlayGameState state = new PlayGameState();

    /**
     * Constructor for PlayGameViewModel, inherits from ViewModel
     */
    public PlayGameViewModel() {
        super("PLAY GAME");
    }

    /**
     * @return the current state of playGame
     */
    public PlayGameState getState() {
        return state;
    }

    /**
     * Fires property change to view model
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("PLAYGAMESTATE", null, this.state);
    }

    /**
     * Adds propertychangelistener for support
     *
     * @param listener the listener that listens for property changes
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}

