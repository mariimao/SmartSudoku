package use_case.leaderboard;

public class LeaderboardInputData {

    final private String user;

    final private int sortingMethod;

    final boolean userView;
    public LeaderboardInputData(String user, int sortingMethod, boolean userView){
        this.user = user;
        this.sortingMethod = sortingMethod;
        this.userView = userView;
    }

    String getUser() {
        return user;
    }

    int getSortingMethod() {
        return sortingMethod;
    }

    boolean getUserView() {
        return userView;
    }
}
