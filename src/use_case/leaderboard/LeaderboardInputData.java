package use_case.leaderboard;

public class LeaderboardInputData {

    final private String user;

    final private String sortingMethod;

    final boolean userView;
    public LeaderboardInputData(String user, String sortingMethod, boolean userView){
        this.user = user;
        this.sortingMethod = sortingMethod;
        this.userView = userView;
    }

    String getUser() {
        return user;
    }

    String getSortingMethod() {
        return sortingMethod;
    }

    boolean getUserView() {
        return userView;
    }
}