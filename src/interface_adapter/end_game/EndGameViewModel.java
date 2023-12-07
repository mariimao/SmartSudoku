package interface_adapter.end_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This is the end game view model class.
 */
public class EndGameViewModel extends ViewModel {
    public static final String MENU_BUTTON_LABEL = "Menu";
    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private EndGameState state = new EndGameState();

    /**
     * Initializes the end game view model
     */
    public EndGameViewModel() {
        super("end game view");
    }

    /**
     * When a property changes, then we can take action.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Detects when a property has changed
     *
     * @param listener      the listener, is a PropertyChangeListener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Below are the getter and setters //
    public EndGameState getState() {
        return this.state;
    }

    public void setState(EndGameState state) {
        this.state = state;
    }

}

