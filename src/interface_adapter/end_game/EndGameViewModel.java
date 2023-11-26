package interface_adapter.end_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EndGameViewModel extends ViewModel {
    public static final String MENU_BUTTON_LABEL = "Menu";
    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";

    private EndGameState state = new EndGameState();

    public EndGameViewModel() {super("end game view");}

    public void setState(EndGameState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public EndGameState getState() {
        return this.state;
    }

}

