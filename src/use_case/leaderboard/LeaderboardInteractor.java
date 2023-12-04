package use_case.leaderboard;

import data_access.UserDAO;
import entity.leaderboard.Leaderboard;
import entity.leaderboard.LeaderboardByRank;
import entity.user.User;

import java.util.*;

/**
 * Class representing the interactor for the Leaderboard usecase. This class implements the LeaderboardInputBoundary.
 */
public class LeaderboardInteractor  implements LeaderboardInputBoundary{

    final LeaderboardDataAccessInterface leaderboardDataAccessInterface;
    final LeaderboardOutputBoundary leaderboardPresenter;

    /**
     * Constructor for a LeaderboardInteractor object.
     * @param leaderboardDataAccessInterface is a LeaderboardDataAccessInterface object
     * @param leaderboardPresenter is a LeaderboardOutputBoundary object
     */
    public LeaderboardInteractor(LeaderboardDataAccessInterface leaderboardDataAccessInterface,
                                 LeaderboardOutputBoundary leaderboardPresenter) {
        this.leaderboardDataAccessInterface = leaderboardDataAccessInterface;
        this.leaderboardPresenter = leaderboardPresenter;
    }

    /**
     * Executes the leaderboard use case.
     * This method prepares a back view if the backView boolean is true. Otherwise, if the sorting method is set to
     * "rank", it will make a Map object of the usernames and User objects to store the account information, and a
     * new LeaderBoardByRank object.
     * It will then put this information into an output data object, and send it to the presenter to display a
     * successful view.
     * If this fails, this means that the account is invalid, and the corresponding error is passed to the presenter.
     * @param leaderboardInputData is a LeaderboardInputData object
     */
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