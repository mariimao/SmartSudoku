package interface_adapter.leaderboard;

public class LeaderboardState {
    private String username = "";
    private int sortingMethod = 0;

    private boolean userView = false;

    public LeaderboardState(LeaderboardState copy) {
        username = copy.username;
        sortingMethod = copy.sortingMethod;
        userView = copy.userView;
    }

    public LeaderboardState(){}

    public String getUsername() {
        return username;
    }

    public int getSortingMethod() {
        return sortingMethod;
    }

    public boolean isUserView() {
        return userView;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSortingMethod(int sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public void setUserView(boolean userView) {
        this.userView = userView;
    }
}