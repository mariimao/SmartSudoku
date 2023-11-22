package use_case.leaderboard;

public class LeaderboardOutputData {
    private boolean userView;

    private int method;

    private String user;

    public LeaderboardOutputData(int method, boolean userView, String user) {
        this.userView = userView;
        this.method = method;
        this.user = user;
    }

    public int getMethod() {
        return method;
    }

    public String getUser() {
        return user;
    }

    public boolean isUserView() {
        return userView;
    }
}