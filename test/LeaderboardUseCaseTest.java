import data_access.UserDAO;
import data_access.UserDAOTest;
import interface_adapter.leaderboard.LeaderboardPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.leaderboard.LeaderboardInteractor;

public class LeaderboardUseCaseTest {

    private LeaderboardInteractor leaderboardInteractor;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAOTest().getUserDAO();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getLeaderboardViewModel());
        leaderboardInteractor = new LeaderboardInteractor(userDAO, leaderboardPresenter);
    }

    @Test
    public void textExecute() {

    }
}

