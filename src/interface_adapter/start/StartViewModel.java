package interface_adapter.start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Start use case. Extends ViewModel Class.
 */
public class StartViewModel extends ViewModel {

    public static final String TITLE_LABEL = "smart sudoku";

    public static final String WELCOME_LABEL = "SUDOKU WITH A TWIST!";

    public static final String SIGNUP_BUTTON_LABEL = "SIGNUP";
    public static final String LOGIN_BUTTON_LABEL = "LOGIN";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private StartState startState = new StartState();

    /**
     * Constructor for StartViewModel, inherits from ViewModel
     */
    public StartViewModel() {
        super("start view");
    }

    /**
     * Fires property change to view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.startState);
    }

    /**
     * Adds propertychangelistener for support
     *
     * @param listener the listener that listens for property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return the start state of the view model
     */
    public StartState getStartState() {
        return this.startState;
    }

    /**
     * Sets the startState to update to view model
     *
     * @param startState the state of the view for start use case
     */
    public void setStartState(StartState startState) {
        this.startState = startState;
    }
}
