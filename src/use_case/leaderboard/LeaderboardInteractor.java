package use_case.leaderboard;

import data_access.UserDAO;
import entity.Leaderboard;

public class LeaderboardInteractor  implements LeaderboardInputBoundary{

    final UserDAO userDAO;
    final Leaderboard leaderboard;
    final LeaderboardOutputBoundary leaderboardPresenter;

    public LeaderboardInteractor(UserDAO userDAO,
                                 Leaderboard leaderboard,
                                 LeaderboardOutputBoundary leaderboardOutputBoundary) {
        this.userDAO = userDAO;
        this.leaderboard = leaderboard;
        this.leaderboardPresenter = leaderboardOutputBoundary;
    }

    @Override
    public void execute(LeaderboardInputData leaderboardInputData) {
        String user = leaderboardInputData.getUser();
        int method = leaderboardInputData.getSortingMethod();
        boolean userView = leaderboardInputData.getUserView();
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(method, userView, user);
        if (userDAO.existsByName(user)) {
            leaderboardPresenter.prepareSuccessView(leaderboardOutputData);
        } else {
            leaderboardPresenter.prepareFailView("Need an account to see leaderboard.");
        }
    }
}
