package interface_adapter.pause_game;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;

public class PauseGameViewModel extends ViewModel {
    public static final String BACK_TO_MENU_BUTTON_LABES = ;
    public static final String LOGOUT_BUTTON_LABEL = ;
    public static final String RESUME_GAME_BUTTON_LABEL = ;

    public PauseGameViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public PauseGameState getState() {
        return null;
    }
}
