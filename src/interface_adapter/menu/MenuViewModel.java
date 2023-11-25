package interface_adapter.menu;

import interface_adapter.ViewModel;
import interface_adapter.start.StartState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Menu";

    public static final String LOAD_BUTTON_LABEL = "LOAD GAME";

    public static final String NEW_BUTTON_LABEL = "NEW GAME";

    public static final String LEADERBOARD_BUTTON_LABEL = "LEADERBOARD";

    public static final String PAST_GAMES_BUTTON_LABEL = "Past Games";

    public static final String CANCEL_BUTTON_LABEL = "Back";

    private MenuState menuState = new MenuState();

    public MenuViewModel() {
        super("menu view");
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.menuState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MenuState getMenuState() { return this.menuState; }

}
