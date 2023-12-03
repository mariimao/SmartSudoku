import app.EndGameUseCaseFactory;
import data_access.UserDAO;
import database.UserDAOTest;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.leaderboard.LeaderboardPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.end_game.EndGameInteractor;
import use_case.leaderboard.LeaderboardInteractor;
import view.EndGameView;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

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

