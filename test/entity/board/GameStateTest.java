package entity.board;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;
public class GameStateTest {

    GameState easyGameState;
    GameState hardGameState;

    @Before
    public void init() {
        easyGameState = new GameState(1);
        hardGameState = new GameState(2);
    }

    @Test
    public void testEasyMakeMove() {
        int row = 0; int col = 0; int val = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (easyGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    row = i; col = j; val = easyGameState.getCurrBoard().getSolutionBoard()[i][j];
                    break;
                }
            }
        }
        easyGameState.makeMove(row, col, val);
        int updatedVal = 0;
        for (Map.Entry<Integer, Boolean> entry : easyGameState.getCurrBoard().getCurrBoard()[row][col].entrySet()) {
            updatedVal = entry.getKey();
        }
        assertEquals(updatedVal, val);
    }

    @Test
    public void testHardMakeMove() {
        int row = 0; int col = 0; int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (hardGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    row = i; col = j; val = hardGameState.getCurrBoard().getSolutionBoard()[i][j];
                    break;
                }
            }
        }
        hardGameState.makeMove(row, col, val);
        int updatedVal = 0;
        for (Map.Entry<Integer, Boolean> entry : hardGameState.getCurrBoard().getCurrBoard()[row][col].entrySet()) {
            updatedVal = entry.getKey();
        }
        assertEquals(updatedVal, val);
    }

    @Test
    public void testEasyScrambler() {
        EasyBoard scrambledBoard = (EasyBoard) easyGameState.scrambleBoard();
        HashMap<Integer, Boolean>[][] currBoard = scrambledBoard.getCurrBoard();
        HashSet<String> seen = new HashSet<>();
        boolean isValidBoard = true;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (!currBoard[i][j].isEmpty()) {
                    String value = "";
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        value = entry.getKey().toString();
                    }
                    String b = "(" + value + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 2 + b + j / 2))
                        isValidBoard = false;
                }
            }
        }
        assertTrue(isValidBoard);
    }

    @Test
    public void testHardScrambler() {
        HardBoard scrambledBoard = (HardBoard) hardGameState.scrambleBoard();
        HashMap<Integer, Boolean>[][] currBoard = scrambledBoard.getCurrBoard();
        HashSet<String> seen = new HashSet<>();
        boolean isValidBoard = true;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (!currBoard[i][j].isEmpty()) {
                    String value = "";
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        value = entry.getKey().toString();
                    }
                    String b = "(" + value + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        isValidBoard = false;
                }
            }
        }
        assertTrue(isValidBoard);
    }

    @Test
    public void testEasyCorrectMove() {
        // TODO: finish this
    }

    @Test
    public void testEasyIncorrectMove() {
        int row = 0; int col = 0; int val = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (easyGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    row = i; col = j; val = -1;
                    break;
                }
            }
        }
        assertFalse(easyGameState.correctMove(row, col, val));
    }

    @Test
    public void testHardCorrectMove() {
        // TODO: Finish this
    }

    @Test
    public void testHardIncorrectMove() {
        int row = 0; int col = 0; int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (hardGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    row = i; col = j; val = -1;
                    break;
                }
            }
        }
        assertFalse(hardGameState.correctMove(row, col, val));
    }

    @Test
    public void testLoseLife() {
        easyGameState.loseLife();
        hardGameState.loseLife();
        assertEquals(easyGameState.getLives(), 4);
        assertEquals(hardGameState.getLives(), 4);
    }

    @Test
    public void testEasySpacesLeft() {
        int numSpacesLeft = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (easyGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    numSpacesLeft++;
                }
            }
        }
        assertEquals(easyGameState.spacesLeft(), numSpacesLeft);
    }

    @Test
    public void testHardSpacesLeft() {
        int numSpacesLeft = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (hardGameState.getCurrBoard().getCurrBoard()[i][j].isEmpty()) {
                    numSpacesLeft++;
                }
            }
        }
        assertEquals(hardGameState.spacesLeft(), numSpacesLeft);
    }

    @After
    public void testGameOver() {
        easyGameState.setCurrBoard("2F3F4F1F4F1F2F3F3F4F1F2F1F2F3F4F");
        hardGameState.setCurrBoard("6F3F5F9F4F7F2F1F8F1F7F9F2F8F3F5F4F6F2F4F8F6F1F5F3F9F7F9F2F4F8F7F1F6F3F5F8F5F" +
                        "7F3F9F6F4F2F1F3F1F6F4F5F2F7F8F9F5F8F1F7F3F4F9F6F2F4F9F2F5F6F8F1F7F3F7F6F3F1F2F9F8F5F4F");
        assertTrue(easyGameState.gameOver());
        assertTrue(hardGameState.gameOver());
    }

}
