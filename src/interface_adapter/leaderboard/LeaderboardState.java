package interface_adapter.leaderboard;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LeaderboardState {
    private String username = "";
    private String error = null;
    private String sortingMethod = "rank"; // default

    private SortedMap<Object, Object> leaderboard = new TreeMap<>();


    public LeaderboardState(LeaderboardState copy) {
        username = copy.username;
        sortingMethod = copy.sortingMethod;
        leaderboard = copy.leaderboard;
    }

    public LeaderboardState(){}

    public String getUsername() {
        return  username;
    }
    public String getError() {
        return error;
    }

    public String getSortingMethod() {
        return sortingMethod;
    }

    public SortedMap<Object, Object> getLeaderboard() {
        return leaderboard;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setSortingMethod(String sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public void setLeaderboard(SortedMap<Object, Object> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}