package interface_adapter.new_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewGameViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "New Game Created Successfully";
    public static final String FAIL_MESSAGE = "Unable to Create New Game. Try Again";

    public static final String SPOTIFY_LABEL = "Choose a song (may take time to load)";

    public static final String SEARCH_BUTTON = "Search";
    private NewGameState state = new NewGameState();

    public NewGameViewModel() {super("new game view");}
    public NewGameState getState() {return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

}
