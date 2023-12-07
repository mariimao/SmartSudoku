import app.PausedGameUseCaseFactory;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.pause_game.*;
import use_case.play_music.PlayMusicDataAccessInterface;
import view.PausedGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PauseGameTest {

    private PausedGameView pausedGameView;
    private Component[] pauseGameComponents;

    @Before
    public void init() throws Exception {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        PauseGameViewModel pauseGameViewModel = new PauseGameViewModel();
        pausedGameView = PausedGameUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                pauseGameViewModel, useCaseTestObjects.getStartViewModel(), useCaseTestObjects.getMenuViewModel(),
                useCaseTestObjects.getSignupViewModel(), useCaseTestObjects.getLoginViewModel(),
                useCaseTestObjects.getResumeGameViewModel(), useCaseTestObjects.getPlayGameViewModel(),
                userDAO, spotifyDAO);

        assert pausedGameView != null;
        pauseGameComponents = pausedGameView.getComponents();
    }

    @Test
    public void testInteractorExecutes() throws IOException {
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
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        PlayMusicDataAccessInterface playMusicDataAccessInterface = spotifyDAO;

        PauseGameDataAccessInterface userDataBase = userDataAccessObject;
        PauseGameOutputBoundary creationPresenter = new PauseGameOutputBoundary() {

            @Override
            public void prepareSuccessView(PauseGameOutputData pauseGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        GameState gameState = new GameState(1);
        PauseGameInputBoundary interactor = new PauseGameInteractor(userDataBase, playMusicDataAccessInterface, creationPresenter);
        PauseGameController pauseGameController = new PauseGameController(interactor);
        pauseGameController.execute("testUser", gameState, gameState.getPastStates());
    }


    @Test
    public void testGetCurrentGame() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        GameState gameState = new GameState(1);
        user.setPausedGame(gameState);
        PauseGameOutputData outputData = new PauseGameOutputData(user, false);
        assertEquals(outputData.getCurrentGame(), gameState);
    }

    @Test
    public void testGetUser() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        PauseGameOutputData outputData = new PauseGameOutputData(user, false);
        assertEquals(outputData.getUser(), user);
        GameState gameState = new GameState(1);
        PauseGameInputData pauseGameInputData = new PauseGameInputData("testUser", gameState, gameState.getPastStates());
        assertEquals(pauseGameInputData.getUsername(), "testUser");
    }

    @Test
    public void testPauseGameView() {
        JFrame jf = new JFrame();
        jf.setContentPane(pausedGameView);
        jf.pack();
        jf.setVisible(true);
        JPanel buttons = (JPanel) pauseGameComponents[1];
        view.CustomButton menuButton = (view.CustomButton) buttons.getComponent(0);
        view.CustomButton logoutButton = (view.CustomButton) buttons.getComponent(1);
        view.CustomButton resumeButton = (view.CustomButton) buttons.getComponent(2);

        menuButton.doClick();
        logoutButton.doClick();
        createCloseTimer().start();
        resumeButton.doClick();
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
