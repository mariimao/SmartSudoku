package interface_adapter.pause_game;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PauseGameViewModel extends ViewModel {
    public static final String BACK_TO_MENU_BUTTON_LABEL = "Go Back to Menu";
    public static final String LOGOUT_BUTTON_LABEL = "Logout";
    public static final String RESUME_GAME_BUTTON_LABEL = "Resume Game";

    private PauseGameState state = new PauseGameState();


    public PauseGameViewModel() {
        super("Paused Game");
    }

    public void setState(PauseGameState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public PauseGameState getState() {
        return state;
    }
}
