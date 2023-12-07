import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.resume_game.ResumeGameController;
import org.junit.Test;
import use_case.play_music.PlayMusicDataAccessInterface;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.resume_game.ResumeGameInteractor;
import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.resume_game.ResumeGameOutputData;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResumeTest {

    @Test
    public void testInteractorExecutes() throws IOException {
        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
            Map<LocalTime, Integer> scores = new HashMap<>();
            scores.put(LocalTime.now(), 4);
            CommonUser user = new CommonUser("testUser", "testPassword", scores);
            GameState gameState = new GameState(1);
            user.setPausedGame(gameState);
            userDataAccessObject.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        ResumeGameDataAccessInterface userDataBase = userDataAccessObject;
        PlayMusicDataAccessInterface playMusicDataAccessInterface = spotifyDAO;
        ResumeGameOutputBoundary creationPresenter = new ResumeGameOutputBoundary() {
            @Override
            public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };
        ResumeGameInteractor interactor = new ResumeGameInteractor(userDataBase, playMusicDataAccessInterface, creationPresenter);
        ResumeGameController resumeGameController = new ResumeGameController(interactor);
        resumeGameController.execute("testUser");
    }

    @Test
    public void testInteractorFails() throws IOException {
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

        ResumeGameDataAccessInterface userDataBase = userDataAccessObject;
        ResumeGameOutputBoundary creationPresenter = new ResumeGameOutputBoundary() {
            @Override
            public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {
                fail("Use case passed but is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(true);
            }
        };
        ResumeGameInteractor interactor = new ResumeGameInteractor(userDataBase, playMusicDataAccessInterface, creationPresenter);
        ResumeGameController resumeGameController = new ResumeGameController(interactor);
        resumeGameController.execute("testUser");
    }

    @Test
    public void testInteractorFails2() throws IOException {
        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SpotifyDAO spotifyDAO = new SpotifyDAO();
        PlayMusicDataAccessInterface playMusicDataAccessInterface = spotifyDAO;

        ResumeGameDataAccessInterface userDataBase = userDataAccessObject;
        ResumeGameOutputBoundary creationPresenter = new ResumeGameOutputBoundary() {
            @Override
            public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {
                fail("Use case passed but is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(true);
            }
        };
        ResumeGameInteractor interactor = new ResumeGameInteractor(userDataBase, playMusicDataAccessInterface, creationPresenter);
        ResumeGameController resumeGameController = new ResumeGameController(interactor);
        resumeGameController.execute("testUser2");
    }

    @Test
    public void testGetPausedGame() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        GameState gameState = new GameState(1);
        user.setPausedGame(gameState);
        ResumeGameOutputData outputData = new ResumeGameOutputData(user, false);
        assertEquals(outputData.getPausedGame(), gameState);
    }

}
