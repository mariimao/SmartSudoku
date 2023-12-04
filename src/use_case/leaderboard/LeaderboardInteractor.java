package use_case.leaderboard;

import data_access.UserDAO;
import entity.leaderboard.Leaderboard;
import entity.leaderboard.LeaderboardByRank;
import entity.user.User;

import java.util.*;

public class LeaderboardInteractor  implements LeaderboardInputBoundary{

    final LeaderboardDataAccessInterface leaderboardDataAccessInterface;
    final LeaderboardOutputBoundary leaderboardPresenter;

    public LeaderboardInteractor(LeaderboardDataAccessInterface leaderboardDataAccessInterface,
                                 LeaderboardOutputBoundary leaderboardPresenter) {
        this.leaderboardDataAccessInterface = leaderboardDataAccessInterface;
        this.leaderboardPresenter = leaderboardPresenter;
    }

    @Override
    public void execute(LeaderboardInputData leaderboardInputData) {
        String user = leaderboardInputData.getUser();
        String method = leaderboardInputData.getSortingMethod();
        boolean userView = leaderboardInputData.getUserView();
        boolean backView = leaderboardInputData.getBackView();
        Leaderboard leaderboard = null;
        SortedMap<Object, Object> output = null;
        if (backView) {
            leaderboardPresenter.prepareBackView();
        } else {
            if (leaderboardDataAccessInterface.existsByName(user)) {
                if (method=="rank") { // currently only one sorting method
                    Map<String, User> accounts = leaderboardDataAccessInterface.getAccounts();
                    leaderboard = new LeaderboardByRank(accounts);
                    output = leaderboard.getLeaderboard();
                }
                if (userView && leaderboard != null) {
                    output = leaderboard.getUserView(user);
                }
                LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(output);
                leaderboardPresenter.prepareSuccessView(leaderboardOutputData);
            } else {
                leaderboardPresenter.prepareFailView("Need an account to see leaderboard.");
            }
        }
    }
}