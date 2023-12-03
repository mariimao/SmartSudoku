package interface_adapter.play_game;

import interface_adapter.ViewModel;
import interface_adapter.new_game.NewGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayGameViewModel extends ViewModel {
    public static final String FAIL_MESSAGE = "Unable to Continue PLaying Game. Try Again";
    private PlayGameState state = new PlayGameState();
    public PlayGameViewModel() {
        super("PLAY GAME");
    } //TODO: change to match final boardView implementation

    public PlayGameState getState() {return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("PLAYGAMESTATE", null, this.state);}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

}

