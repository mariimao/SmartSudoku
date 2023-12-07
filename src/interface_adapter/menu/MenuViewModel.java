package interface_adapter.menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Spotify use case. Extends ViewModel Class.
 */
public class MenuViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Menu";

    public static final String LOAD_BUTTON_LABEL = "LOAD GAME";

    public static final String NEW_BUTTON_LABEL = "NEW GAME";

    public static final String LEADERBOARD_BUTTON_LABEL = "LEADERBOARD";

    public static final String PAST_GAMES_BUTTON_LABEL = "Past Games";

    public static final String CANCEL_BUTTON_LABEL = "Back";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private MenuState menuState = new MenuState();

    /**
     * Constructor for MenuViewModel, inherits from ViewModel
     */
    public MenuViewModel() {
        super("menu view");
    }

    /**
     * Fires property change to view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.menuState);
    }

    /**
     * Adds propertychangelistener for support
     *
     * @param listener the listener that listens for property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return the menu state
     */
    public MenuState getMenuState() {
        return this.menuState;
    }

    /**
     * Sets the menu state to a new version
     *
     * @param menuState
     */
    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

}
