package use_case.leaderboard;

/**
 * Class representing the InputBoundary for the Leaderboard usecase. This class is implemented by the LeaderboardInteractor.
 */
public interface LeaderboardInputBoundary {

    /**
     * Executes the Leaderboard use case.
     * @param leaderboardInputData is an LeaderboardInputData object
     */
    void execute(LeaderboardInputData leaderboardInputData);

}