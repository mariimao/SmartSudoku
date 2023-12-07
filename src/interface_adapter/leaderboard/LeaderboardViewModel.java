package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The LoginViewModel class.
 */
public class LeaderboardViewModel extends ViewModel {
    public static String TITLE_LABEL = "Leaderboard";
    public static String MENU_BUTTON_LABEL = "Menu";
    public static String SORT_BY_CHOICE_LABEL = "Sort by...";
    public static String USER_BUTTON_LABEL = "See my results";
    public static String NO_SCORES_LABEL = "No scores available yet.";
    public static String[] CHOICES = {"Rank"};

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LeaderboardState leaderboardState = new LeaderboardState();

    /**
     * Constructor for LeaderboardViewModel. Inherits and sets the view name.
     */
    public LeaderboardViewModel() {
        super("leaderboard view");
    }

    /**
     * Takes action is property changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("leaderboard", null, this.leaderboardState);
    }


    /**
     * Adds a property change listener.
     *
     * @param listener is a PropertyChangeListener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     *
     * @return the current leaderboard state
     */
    public LeaderboardState getLeaderboardState() {
        return leaderboardState;
    }


    /**
     * Sets the leaderboard state to an updated version
     * @param leaderboardState
     */
    public void setLeaderboardState(LeaderboardState leaderboardState) {
        this.leaderboardState = leaderboardState;
    }
}