package use_case.leaderboard;

public class LeaderboardInputData {

    final private String user;

    final private String sortingMethod;

    final boolean userView;

    final boolean backView;

    public LeaderboardInputData(String user, String sortingMethod, boolean userView, boolean backView){
        this.user = user;
        this.sortingMethod = sortingMethod;
        this.userView = userView;
        this.backView = backView;
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

    public boolean getBackView() {
        return backView;
    }
}