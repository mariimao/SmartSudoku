package use_case.leaderboard;

/**
 * This class is the interface of the Leaderboard output boundary. It is implemented by LeaderboardPresenter.
 */
public interface LeaderboardOutputBoundary {

    /**
     * Called when Leaderboard runs successfully - prepares a success view.
     *
     * @param leaderboard is an LeaderboardOutputData object
     */
    void prepareSuccessView(LeaderboardOutputData leaderboard);

    /**
     * Called when Leaderboard doesn't run successfully - prepares a fail view.
     *
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);

    /**
     * If the view type is Back, then a backView object is prepared.
     */
    void prepareBackView();
}