package interface_adapter.easy_game;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.pause_game.PauseGameState;
import use_case.user_move.UserMoveOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EasyGameViewModel extends ViewModel {

    public static final String ROW_LABEL = "Row";
    public static final String COLUMN_LABEL = "Column";
    public static final String VALUE_LABEL = "Value";

    public static final String END_GAME_BUTTON_LABEL = "End Game";
    public static final String MAKE_MOVE_BUTTON_LABEL = "Make Move";
    public static final String PAUSE_GAME_BUTTON_LABEL = "Pause Game";

    private EasyGameState state = new EasyGameState();

    public EasyGameViewModel() {
        super("easy game");
    }

    public void setState(EasyGameState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public EasyGameState getState() {
        return state;
    }



}
