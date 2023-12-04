import app.LeaderboardUseCaseFactory;
import app.PausedGameUseCaseFactory;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import data_access.UserDAOTest;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.leaderboard.*;
import use_case.spotify.*;
import view.LeaderboardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LeaderboardUseCaseTest {

    private LeaderboardInteractor leaderboardInteractor;
    private LeaderboardDataAccessInterface userDataBase;
    private LeaderboardView leaderboardView;
    private Component[] leaderboardComponents;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getMenuViewModel());
        leaderboardInteractor = new LeaderboardInteractor(userDAO, leaderboardPresenter);

        leaderboardView = LeaderboardUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getMenuViewModel(),
                userDAO);

        leaderboardComponents = leaderboardView.getComponents();
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
        LeaderboardController leaderboardController = new LeaderboardController(interactor);
        leaderboardController.execute("name", "rank", true, false);
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

    @Test
    public void testLeaderboardView() {
        JFrame jf = new JFrame();
        jf.setContentPane(leaderboardView); jf.pack(); jf.setVisible(true);
        JPanel buttons = (JPanel) leaderboardComponents[1];
        view.CustomButton resultsButton = (view.CustomButton) buttons.getComponent(0);
        view.CustomButton menuButton = (view.CustomButton) buttons.getComponent(1);

        createCloseTimer().start();
        resultsButton.doClick();
        menuButton.doClick();
        System.out.println("Buttons clicked successfully");
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(300, close);
        t.setRepeats(false);
        return t;
    }
}

