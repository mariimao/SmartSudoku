package data_access;

import com.mongodb.MongoException;
import entity.board.EasyBoard;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
        // Set up your UserDAO instance here
        // You may need to provide the appropriate MongoDB URI and other required parameters
        userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
    }

    @AfterEach
    void tearDown() {
        // Clean up any resources or data created during tests
        userDAO.delete("testUser");
    }

    @Test
    void testAddUser() {
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        EasyBoard easyBoard = new EasyBoard();
        CommonUser user = new CommonUser("testUser", "testPassword", scores);

        userDAO.addUser(user);

        assertTrue(userDAO.existsByName("testUser"));
        assertEquals(user, userDAO.get("testUser"));
    }

    @Test
    void testGetANDSetProgress() {
        // creating dummy user and info
        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 4);
        ArrayList<GameState> gameStates = new ArrayList<>();
        GameState testEasyGame = new GameState(1);
        GameState testHardGame = new GameState(2);
        gameStates.add(testHardGame); gameStates.add(testEasyGame);

        CommonUser user = new CommonUser("testUser", "testPassword", scores);
        String userName = "testUser";
        userDAO.addUser(user);

        // Test that we get null if there is no game that was paused
        assertNull(userDAO.getProgress(user.getName()));

        // Test that equivalent Game States are returned whenever a new paused game is set
        for (GameState gameState : gameStates) {
            user.setPausedGame(gameState);
            userDAO.setProgress(user);
            GameState result = userDAO.getProgress(userName);
            assertEquals(gameState.getPastStates(), result.getPastStates());
            assertEquals(gameState.getLives(), result.getLives());
            assertEquals(gameState.getDifficulty(), result.getDifficulty());
            assertEquals(gameState.getCurrBoard(), result.getCurrBoard());
        }

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    // Add more test methods based on your requirements


}
