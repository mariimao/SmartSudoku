package interface_adapter.leaderboard;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;

public class LeaderboardController {
    // add button to go back to main menu

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary clearUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = clearUseCaseInteractor;
    }

    public void execute(String user, int sortingMethod, boolean userView) {
        LeaderboardInputData leaderboardInputData = new LeaderboardInputData(user, sortingMethod, userView);
        leaderboardUseCaseInteractor.execute(leaderboardInputData);
    }
}
