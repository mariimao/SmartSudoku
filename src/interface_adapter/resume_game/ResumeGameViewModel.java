package interface_adapter.resume_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Resume Game use case. Extends ViewModel Class.
 */
public class ResumeGameViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "Game Resumed Successfully";
    public static final String FAIL_MESSAGE = "Game Not Resumed. Try Again";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final ResumeGameState state = new ResumeGameState();


    /**
     * Constructor for ResumeGameViewModel, inherits from ViewModel
     */
    public ResumeGameViewModel() {
        super("Resume Game");
    }

    /**
     * @return the current state of resume game
     */
    public ResumeGameState getState() {
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
