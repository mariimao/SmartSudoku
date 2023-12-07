package use_case.leaderboard;

/**
 * Class representing the input data of the leaderboard.
 */
public class LeaderboardInputData {

    final boolean userView;
    final boolean backView;
    final private String user;
    final private String sortingMethod;

    /**
     * Constructor for the LeaderboardInputData object.
     *
     * @param user          is a String representing the username
     * @param sortingMethod is a String representing the sorting method
     * @param userView      is a boolean, representing whether the userView should be present
     * @param backView      is a boolean, representing whether the backView should be present
     */
    public LeaderboardInputData(String user, String sortingMethod, boolean userView, boolean backView) {
        this.user = user;
        this.sortingMethod = sortingMethod;
        this.userView = userView;
        this.backView = backView;
    }

    /* ----- Getters and setters ----- */
    public String getUser() {
        return user;
    }

    public String getSortingMethod() {
        return sortingMethod;
    }

    public boolean getUserView() {
        return userView;
    }

    public boolean getBackView() {
        return backView;
    }
}