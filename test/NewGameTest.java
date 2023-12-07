import app.NewGameUseCaseFactory;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.new_game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewGameTest {

    private Component[] newGameComponents;
    private JPanel newGameView;

    @Before
    public void init() throws Exception {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        userDAO.addUser(user);
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        NewGameViewModel newGameViewModel = new NewGameViewModel();
        newGameViewModel.getState().setUser(user);
        newGameView = NewGameUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                newGameViewModel, userDAO, useCaseTestObjects.getPlayGameViewModel(),
                useCaseTestObjects.getLoginViewModel(), useCaseTestObjects.getSpotifyViewModel(),
                spotifyDAO);

        newGameComponents = newGameView.getComponents();
    }

    @Test
    public void testEasyGameButtonPresent() {
        JPanel buttons = (JPanel) newGameComponents[1];
        JButton button = (JButton) buttons.getComponent(0);
        assert (button.getText().equals("EASY GAME"));
    }

    @Test
    public void testHardGameButtonPresent() {
        JPanel buttons = (JPanel) newGameComponents[1];
        JButton button = (JButton) buttons.getComponent(1);
        assert (button.getText().equals("HARD GAME"));
    }

    @Test
    public void testSearchButtonPresent() {
        JPanel buttons = (JPanel) newGameComponents[2];
        JButton button = (JButton) buttons.getComponent(1);
        assert (button.getText().equals("SEARCH"));
    }

    @Test
    public void testInteractorExecutes() {
        UserDAO userDataAccessObject;
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
        NewGameDataAccessInterface userDataBase = userDataAccessObject;
        NewGameOutputBoundary creationPresenter = new NewGameOutputBoundary() {

            @Override
            public void prepareSuccessView(NewGameOutputData newGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        NewGameInputBoundary interactor = new NewGameInteractor(userDataBase, creationPresenter);
        NewGameController newGameController = new NewGameController(interactor);
        newGameController.execute("testUser", 1);
    }

    @Test
    public void testInteractorFailExecutes() {
        NewGameDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NewGameDataAccessInterface userDataBase = userDataAccessObject;
        NewGameOutputBoundary creationPresenter = new NewGameOutputBoundary() {

            @Override
            public void prepareSuccessView(NewGameOutputData newGameOutputData) {
                fail("Use case failed unexpectedly.");
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("Use case failed, as expected.");
            }
        };

        NewGameInputBoundary interactor = new NewGameInteractor(userDataBase, creationPresenter);
        NewGameController newGameController = new NewGameController(interactor);
        newGameController.execute("tester", 1);
    }

    @Test
    public void testGetNewGame() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        GameState gameState = new GameState(1);
        NewGameOutputData outputData = new NewGameOutputData(user, gameState);
        assertEquals(outputData.getNewGame(), gameState);
    }

    @Test
    public void testGetUser() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        GameState gameState = new GameState(1);
        NewGameOutputData outputData = new NewGameOutputData(user, gameState);
        assertEquals(outputData.getUser(), user);
    }

    @Test
    public void testNewGameView() {
        NewGameViewModel viewModel = new NewGameViewModel();
        JFrame jf = new JFrame();
        jf.setContentPane(newGameView);
        jf.pack();
        jf.setVisible(true);
        Component[] newGameComponents = newGameView.getComponents();
        JPanel buttons = (JPanel) newGameComponents[1];
        view.CustomButton easyGame = (view.CustomButton) buttons.getComponent(0);
        view.CustomButton hardGame = (view.CustomButton) buttons.getComponent(1);
        JPanel buttons2 = (JPanel) newGameComponents[2];
        view.CustomButton searchSong = (view.CustomButton) buttons2.getComponent(1);

        createCloseTimer().start();
        easyGame.doClick();
        createCloseTimer().start();
        hardGame.doClick();
        searchSong.doClick();
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog) window;

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
