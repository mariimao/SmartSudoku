package interface_adapter.new_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for New Game use case. Extends ViewModel Class.
 */
public class NewGameViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "New Game Created Successfully";
    public static final String FAIL_MESSAGE = "Unable to Create New Game. Try Again";

    public static final String SPOTIFY_LABEL = "Choose a song (may take time to load)";

    public static final String SEARCH_BUTTON = "Search";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final NewGameState state = new NewGameState();

    /**
     * Constructor for NewGameViewModel, inherits from ViewModel
     */
    public NewGameViewModel() {
        super("new game view");
    }

    /**
     * @return the new game state
     */
    public NewGameState getState() {
        return state;
    }

    /**
     * Fires property change to view model
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
