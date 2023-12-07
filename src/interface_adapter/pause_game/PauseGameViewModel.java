package interface_adapter.pause_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Pause Game use case. Extends ViewModel Class.
 */
public class PauseGameViewModel extends ViewModel {
    public static final String BACK_TO_MENU_BUTTON_LABEL = "Go Back to Menu";
    public static final String LOGOUT_BUTTON_LABEL = "Logout";
    public static final String RESUME_GAME_BUTTON_LABEL = "Resume Game";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private PauseGameState state = new PauseGameState();

    /**
     * Constructor for pause game view model
     */
    public PauseGameViewModel() {
        super("GAME PAUSED");
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

    /**
     * @return the state of pauseGameState for view model to update the view
     */
    public PauseGameState getState() {
        return state;
    }

    /**
     * Sets the pause game state to a new version
     *
     * @param state
     */
    public void setState(PauseGameState state) {
        this.state = state;
    }
}
