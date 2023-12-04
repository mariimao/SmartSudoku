import data_access.SpotifyDAO;
import data_access.UserDAO;
import data_access.UserDAOTest;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.leaderboard.LeaderboardPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.leaderboard.*;
import use_case.spotify.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LeaderboardUseCaseTest {

    private LeaderboardInteractor leaderboardInteractor;
    private LeaderboardDataAccessInterface userDataBase;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAOTest().getUserDAO();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getLeaderboardViewModel());
        leaderboardInteractor = new LeaderboardInteractor(userDAO, leaderboardPresenter);
    }

    @Test
    public void testSucceedLeaderboard() {
        LeaderboardInputData inputData = new LeaderboardInputData("name", "", true, false);
        LeaderboardDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        UserFactory userFactory = new CommonUserFactory();
        Map<LocalTime, Integer> scores_test = new HashMap<>();
        User user = userFactory.create("name", "pass", scores_test);
        userDataBase.addUser(user);

        LeaderboardOutputBoundary leaderboardPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(LeaderboardOutputData leaderboardOutputData) {
                //assertTrue(leaderboardOutputData.getLeaderboard() != "");
                assertTrue(userDataAccessObject.existsByName("name"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }

            @Override
            public void prepareBackView() {
                fail("Use case failed but is unexpected.");
            }
        };

        LeaderboardInteractor interactor = new LeaderboardInteractor(userDataAccessObject, leaderboardPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testFailedLeaderboard() {
        LeaderboardInputData inputData = new LeaderboardInputData("hello", "", true, false);
        LeaderboardDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        LeaderboardOutputBoundary leaderboardPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(LeaderboardOutputData leaderboardOutputData) {
                fail("Use case success but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertFalse(userDataAccessObject.existsByName("hello"));
            }

            @Override
            public void prepareBackView() {
                fail("Use case failed but is unexpected.");
            }
        };

        LeaderboardInteractor interactor = new LeaderboardInteractor(userDataAccessObject, leaderboardPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testBackViewSuccess() {
        LeaderboardInputData inputData = new LeaderboardInputData("name", "Rank", false, true);
        LeaderboardDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        UserFactory userFactory = new CommonUserFactory();
        Map<LocalTime, Integer> scores_test = new HashMap<>();
        User user = userFactory.create("name", "pass", scores_test);
        userDataBase.addUser(user);

        LeaderboardOutputBoundary leaderboardPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(LeaderboardOutputData leaderboardOutputData) {
                fail("Use case userview succeess but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }

            @Override
            public void prepareBackView() {
                assertTrue(inputData.getBackView());
            }
        };

        LeaderboardInteractor interactor = new LeaderboardInteractor(userDataAccessObject, leaderboardPresenter);
        interactor.execute(inputData);
    }
}

