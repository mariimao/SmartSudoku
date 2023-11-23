package interface_adapter.leaderboard;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;

public class LeaderboardController {
    // add button to go back to main menu

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }

    public void execute(String user, String sortingMethod, boolean userView, boolean backView) {
        LeaderboardInputData leaderboardInputData = new LeaderboardInputData(user, sortingMethod, userView, backView);
        leaderboardUseCaseInteractor.execute(leaderboardInputData);
    }
}