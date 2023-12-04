import app.EndGameUseCaseFactory;
import data_access.UserDAO;
import data_access.UserDAOTest;
import entity.Scores;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.play_game.PlayGameController;
import org.junit.Before;
import org.junit.Test;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInteractor;
import use_case.play_game.PlayGameOutputBoundary;
import use_case.play_game.PlayGameOutputData;
import view.EndGameView;
import view.LeaderboardView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EndGameTest {

    private EndGameInteractor endGameInteractor;
    private Component[] endGameComponents;
    private EndGameView endGameView;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAOTest().getUserDAO();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        endGameView = EndGameUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getEndGameViewModel(), userDAO, useCaseTestObjects.getMenuViewModel(),
                useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getStartViewModel(),
                useCaseTestObjects.getSignupViewModel(), useCaseTestObjects.getLoginViewModel());
        EndGamePresenter endGamePresenter = new EndGamePresenter(useCaseTestObjects.getLeaderboardViewModel(),
                useCaseTestObjects.getMenuViewModel(), useCaseTestObjects.getEndGameViewModel(),
                useCaseTestObjects.getViewManagerModel());
        endGameInteractor = new EndGameInteractor(userDAO, endGamePresenter);
        endGameComponents = endGameView.getComponents();
    }

    @Test
    public void testInteractorExecutes() {
        UserDAO userDataAccessObject;
        GameState gameState = new GameState(1);
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
            Map<LocalTime, Integer> scores = new HashMap<>();
            scores.put(LocalTime.now(), 4);
            CommonUser user = new CommonUser("testUser", "testPassword", scores);
            userDataAccessObject.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        EndGameDataAccessInterface userDataBase = userDataAccessObject;
        EndGameOutputBoundary creationPresenter = new EndGameOutputBoundary() {
            @Override
            public void prepareSuccessView(EndGameOutputData endGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };
        EndGameInteractor interactor = new EndGameInteractor(userDataBase, creationPresenter);
        EndGameController endGameController = new EndGameController(interactor);
        Scores scores = new Scores();
        endGameController.execute("testUser", gameState, 100, 5, scores);
    }

    @Test
    public void testEndGameView() {
        JFrame jf = new JFrame();
        jf.setContentPane(endGameView); jf.pack(); jf.setVisible(true);
        JPanel buttons = (JPanel) endGameComponents[1];
        view.CustomButton menuButton = (view.CustomButton) buttons.getComponent(0);
        view.CustomButton leaderBoardButton = (view.CustomButton) buttons.getComponent(1);

        menuButton.doClick();
    }
}

