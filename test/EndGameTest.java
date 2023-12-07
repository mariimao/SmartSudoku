import app.EndGameUseCaseFactory;
import data_access.UserDAO;
import data_access.UserDAOTest;
import entity.Scores;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.end_game.EndGameDataAccessInterface;
import use_case.end_game.EndGameInteractor;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;
import view.EndGameView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EndGameTest {
    private Component[] endGameComponents;
    private EndGameView endGameView;
    @Before
    public void init() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAOTest().getUserDAO();
        EndGameViewModel endGameViewModel = new EndGameViewModel();
        endGameView = EndGameUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getEndGameViewModel(), userDAO, useCaseTestObjects.getMenuViewModel(),
                useCaseTestObjects.getLeaderboardViewModel());
        EndGamePresenter endGamePresenter = new EndGamePresenter(useCaseTestObjects.getEndGameViewModel(),
                useCaseTestObjects.getViewManagerModel());
        EndGameInteractor endGameInteractor = new EndGameInteractor(userDAO, endGamePresenter);
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

    @Test
    public void testPresenter() {
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

        EndGamePresenter endGamePresenter = new EndGamePresenter(new EndGameViewModel(), new ViewManagerModel());

        EndGameOutputData endGameOutputData = new EndGameOutputData(userDataAccessObject.get("testUser"), 10, gameState);
        endGamePresenter.prepareSuccessView(endGameOutputData);

        endGamePresenter.prepareFailView("Unable to end the game.");
    }

    @Test
    public void testViewModel() {
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

        EndGameViewModel endGameViewModel = new EndGameViewModel();
        EndGameState endGameState = new EndGameState();
        endGameState.setTime(5);
        endGameViewModel.setState(endGameState);
        assert( endGameState.getTime()==5);

        assert(endGameState.equals(endGameViewModel.getState()));

        endGameState.setFinalGame(gameState);
        endGameViewModel.setState(endGameState);
    }
}

