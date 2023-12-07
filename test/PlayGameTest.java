import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.play_game.PlayGameController;
import org.junit.Test;
import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_game.PlayGameInteractor;
import use_case.play_game.PlayGameOutputBoundary;
import use_case.play_game.PlayGameOutputData;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PlayGameTest {
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
            user.setPausedGame(gameState);
            userDataAccessObject.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PlayGameDataAccessInterface userDataBase = userDataAccessObject;
        PlayGameOutputBoundary creationPresenter = new PlayGameOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayGameOutputData newGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };
        PlayGameInteractor interactor = new PlayGameInteractor(userDataBase, creationPresenter);
        PlayGameController playGameController = new PlayGameController(interactor);
        playGameController.execute("testUser", gameState, 1);
    }

    @Test
    public void testInteractorExecutes2() {
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
        PlayGameDataAccessInterface userDataBase = userDataAccessObject;
        PlayGameOutputBoundary creationPresenter = new PlayGameOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayGameOutputData newGameOutputData) {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };
        PlayGameInteractor interactor = new PlayGameInteractor(userDataBase, creationPresenter);
        PlayGameController playGameController = new PlayGameController(interactor);
        playGameController.execute("testUser", null, 1);
    }

}
