package interface_adapter.leaderboard;

import entity.leaderboard.Leaderboard;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import view.LeaderboardView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.SortedMap;

public class LeaderboardViewModel extends ViewModel {
    public static String TITLE_LABEL = "Leaderboard";

    public static String MENU_BUTTON_LABEL = "Menu";

    public static String SORT_BY_CHOICE_LABEL = "Sort by...";

    public static String USER_BUTTON_LABEL = "See my results";

    public static String NO_SCORES_LABEL = "No scores available yet.";

    public static String[] CHOICES = {"Rank"};

    private LeaderboardState leaderboardState = new LeaderboardState();

    public LeaderboardViewModel() {
        super("leaderboard view");
    }

    public SortedMap<Object, Object> getLeaderboard() {
        return getLeaderboardState().getLeaderboard();
    }

    public void setLeaderboardState(LeaderboardState leaderboardState) {
        this.leaderboardState = leaderboardState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("leaderboard", null, this.leaderboardState);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LeaderboardState getLeaderboardState() { return leaderboardState; }

}