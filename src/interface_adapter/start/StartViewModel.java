package interface_adapter.start;

import interface_adapter.ViewModel;
import interface_adapter.start.StartState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StartViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Welcome to Sudoku";

    public static final String START_BUTTON_LABEL = "start";

    private StartState startState = new StartState();

    public StartViewModel() {
        super("start view");
    }

    public void setStartState(StartState startState) {
        this.startState = startState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.startState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public StartState getStartState() { return this.startState; }
}
