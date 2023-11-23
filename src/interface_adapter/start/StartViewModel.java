package interface_adapter.start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StartViewModel extends ViewModel {

    public static final String TITLE_LABEL = "smart sudoku";

    public static final String WELCOME_LABEL = "SUDOKU WITH A TWIST!";

    public static final String SIGNUP_BUTTON_LABEL = "SIGNUP";
    public static final String LOGIN_BUTTON_LABEL = "LOGIN";

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
