package interface_adapter.leaderboard;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The LeaderboardState class. Represents the current state of the leaderboard.
 */
public class LeaderboardState {
    private String username = "";
    private String error = null;
    private String sortingMethod = "rank"; // default
    private SortedMap<Object, Object> leaderboard = new TreeMap<>();

    /**
     * Constructor for a LeaderboardState object that copies over info from another LeaderboardState
     *
     * @param copy
     */
    public LeaderboardState(LeaderboardState copy) {
        username = copy.username;
        sortingMethod = copy.sortingMethod;
        leaderboard = copy.leaderboard;
    }

    /**
     * Constructor a LeaderboardState object. Assumes no older version of Leaderboard was created.
     */
    public LeaderboardState() {
    }

    // Below are the getters and setters //
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSortingMethod() {
        return sortingMethod;
    }

    public void setSortingMethod(String sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public SortedMap<Object, Object> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(SortedMap<Object, Object> leaderboard) {
        this.leaderboard = leaderboard;
    }
}