package use_case.leaderboard;

public interface LeaderboardOutputBoundary {
    void prepareSuccessView(LeaderboardOutputData leaderboard);

    void prepareFailView(String error);
}