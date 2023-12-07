import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.make_move.MakeMoveController;
import org.junit.Test;
import use_case.make_move.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MakeMoveTest {

    @Test
    public void testInteractorExecutes() throws IOException {
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
        MakeMoveDataAccessInterface userDataBase = userDataAccessObject;
        MakeMoveOutputBoundary creationPresenter = new MakeMoveOutputBoundary() {
            @Override
            public GameState prepareSuccessView(MakeMoveOutputData makeMoveOutputData) {
                assertTrue(true);
                return null;
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(true);
            }
        };
        MakeMoveInteractor interactor = new MakeMoveInteractor(userDataBase, creationPresenter, null);
        MakeMoveController makeMoveController = new MakeMoveController(interactor);
        HashMap<Integer, Boolean>[][] currBoard = gameState.getCurrBoard().getCurrBoard();
        ArrayList<Integer> moveInfo = generateMove(currBoard, gameState.getCurrBoard().getSolutionBoard());
        int val = moveInfo.get(0);
        int row = moveInfo.get(1);
        int col = moveInfo.get(2);
        makeMoveController.execute(val, row, col, gameState);
    }

    private ArrayList<Integer> generateMove(HashMap<Integer, Boolean>[][] currBoard, int[][] solutionBoard) {
        ArrayList<Integer> moveInfo = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currBoard[i][j].isEmpty()) {
                    int value = solutionBoard[i][j];
                    moveInfo.add(value);
                    moveInfo.add(i);
                    moveInfo.add(j);
                    return moveInfo;
                }
            }
        }
        return moveInfo;
    }

    @Test
    public void testGetGameBeingPlayed() {
        GameState gameState = new GameState(1);
        MakeMoveOutputData makeMoveOutputData = new MakeMoveOutputData(gameState);
        assertEquals(makeMoveOutputData.getGameBeingPlayed(), gameState);
    }

    @Test
    public void testLostLife() {
        GameState gameState = new GameState(1);
        ArrayList<Integer> moveData = generateMove(gameState.getCurrBoard().getCurrBoard(),
                gameState.getCurrBoard().getSolutionBoard());
        int row = moveData.get(0);
        int col = moveData.get(1);
        int val = moveData.get(2);
        MakeMoveInputData makeMoveInputData = new MakeMoveInputData(row, col, val, gameState);
        makeMoveInputData.loseLife();
        assertEquals(4, makeMoveInputData.getGameBeingPlayed().getLives());
    }

}
