package interface_adapter.resume_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResumeGameViewModel extends ViewModel {
    public static final String SUCCESS_MESSAGE = "Game Resumed Successfully";
    public static final String FAIL_MESSAGE = "Game Not Resumed. Try Again";
    private ResumeGameState state = new ResumeGameState();
    public ResumeGameViewModel() {super("Resume Game");}
    public ResumeGameState getState() {return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

}
