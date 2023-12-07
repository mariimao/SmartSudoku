package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;

/**
 * The controller for leaderboard use case.
 */
public class LeaderboardController {

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    /**
     * Initializes a LeaderboardController object
     *
     * @param leaderboardUseCaseInteractor is a LeaderboardInputBoundary type
     */
    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }

    /**
     * Creates the input data and calls the interactor
     *
     * @param user          The current user
     * @param sortingMethod The sorting method they chose
     * @param userView      If they want to see just their scores or not
     * @param backView      If they want to go back to main menu
     */
    public void execute(String user, String sortingMethod, boolean userView, boolean backView) {
        LeaderboardInputData leaderboardInputData = new LeaderboardInputData(user, sortingMethod, userView, backView);
        leaderboardUseCaseInteractor.execute(leaderboardInputData);
    }
}