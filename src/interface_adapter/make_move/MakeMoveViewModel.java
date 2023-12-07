package interface_adapter.make_move;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View Model for make move. Extends ViewModel.
 */
public class MakeMoveViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "Move Made Successfully";
    public static final String FAIL_MESSAGE = "Unable to Make Move. Try Again";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private final MakeMoveState state = new MakeMoveState();

    /**
     * Constructor for MakeMoveViewModel
     */
    public MakeMoveViewModel() {
        super("MAKE MOVE");
    }

    /**
     * @return the current state of make move
     */
    public MakeMoveState getState() {
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
