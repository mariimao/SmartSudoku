package interface_adapter.make_move;

import interface_adapter.ViewModel;
import interface_adapter.new_game.NewGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MakeMoveViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "Move Made Successfully";
    public static final String FAIL_MESSAGE = "Unable to Make Move. Try Again";

    private MakeMoveState state = new MakeMoveState();

    public MakeMoveViewModel() {super("MAKE MOVE");}
    public MakeMoveState getState() {return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("MAKEMOVESTATE", null, this.state);}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

}
