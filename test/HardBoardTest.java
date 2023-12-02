import entity.board.HardBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class HardBoardTest implements BoardTest{
    private HardBoard hardBoard;
    private int[][] solutionBoard;
    HashMap<Integer, Boolean>[][] currBoard;

    @Before
    public void init() {
        hardBoard = new HardBoard();
        solutionBoard = hardBoard.getSolutionBoard();
        currBoard = hardBoard.getCurrBoard();
    }

    @Test
    public void testValidBoard() {
        HashSet<String> seen = new HashSet<>();
        boolean isValidBoard = true;
        HashMap<Integer, Boolean>[][] currBoard = hardBoard.getCurrBoard();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (!currBoard[i][j].isEmpty()) {
                    String value = "";
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        value = entry.getKey().toString();
                    }
                    String b = "(" + value + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
                        isValidBoard = false;
                }
            }
        }
        assertTrue(isValidBoard);
    }

    @Test
    public void testIsValidSolutions() {
        HashSet<String> seen = new HashSet<>();
        boolean isValidTestBoard = true;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                String b = "(" + solutionBoard[i][j] + ")";
                if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
                    isValidTestBoard = false;
            }
        }
        assertTrue(isValidTestBoard);
    }

    @Test
    public void testGenerateBlankBoard() {
        boolean allBlankValues = true;
        HashMap<Integer, Boolean>[][] blankBoard = hardBoard.generateBlankBoard();
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                if (!blankBoard[i][j].isEmpty()) {
                    allBlankValues = false;
                }
            }
        }
        assertTrue(allBlankValues);
    }

    @Test
    public void testMakeMove() {
        boolean allMovesAreValid = true;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if (currBoard[i][j].isEmpty()) {
                    hardBoard.makeMove(i, j, solutionBoard[j][i]);
                    currBoard = hardBoard.getCurrBoard();
                    int value = 0;
                    for (Map.Entry<Integer, Boolean> entry : currBoard[j][i].entrySet()) {
                        value = entry.getKey();
                    }
                    if (value != solutionBoard[j][i]) {
                        allMovesAreValid = false;
                    }
                }
            }
        }
        assertTrue(allMovesAreValid);
    }

    @After
    public void testNoSpacesLeft() {
        boolean noSpacesLeft = true;

        HashMap<Integer, Boolean>[][] fullBoard = new HashMap[9][9];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                HashMap<Integer, Boolean> value = new HashMap<>();
                value.put(solutionBoard[i][j], true);
                fullBoard[i][j] = value;

                if (currBoard[i][j].isEmpty()) {
                    noSpacesLeft = false;
                }
            }
        }
        assertEquals(noSpacesLeft, hardBoard.noSpacesLeft());
        hardBoard.setBoard(fullBoard);
        assertTrue(hardBoard.noSpacesLeft());
    }
}