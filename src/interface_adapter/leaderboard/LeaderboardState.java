package interface_adapter.leaderboard;

import java.util.SortedMap;

public class LeaderboardState {
    private String username = "";
    private String error = null;
    private String sortingMethod = "";

    private SortedMap<Object, Object> leaderboard = null;


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
}