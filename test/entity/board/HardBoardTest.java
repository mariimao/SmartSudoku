package entity.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class HardBoardTest {
    private HardBoard hardBoard;
    private HardBoard hardBoard2;
    private int[][] solutionBoard2;
    private int[][] solutionBoard;
    HashMap<Integer, Boolean>[][] currBoard;

    @Before
    public void init() {
        hardBoard = new HardBoardFactory().create();
        solutionBoard = hardBoard.getSolutionBoard();
        currBoard = hardBoard.getCurrBoard();
        System.out.println(hardBoard);
        String positions = "09F01F4F07F08F7F009F03F04F6F4F00006F01F5F3F2F08F6F05F00000009F07F0" +
                "06F7F4F3F5F1F008F5F0000000003F5F9F0007F1F7F03F2F0000";
        hardBoard2 = new HardBoardFactory().create(positions);
        solutionBoard2 = hardBoard2.getSolutionBoard();
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
    public void testCorrectMove() {
        boolean correctMoveIsValid = true;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if (!hardBoard.correctMove(i, j, solutionBoard[i][j])) {
                    correctMoveIsValid = false;
                }
            }
        }
        assertTrue(correctMoveIsValid);
    }

    @Test
    public void testSpacesLeft() {
        HardBoard testerBoard = new HardBoard();
        HashMap<Integer, Boolean>[][] setBoard = testerBoard.generateBlankBoard();
        testerBoard.setBoard(setBoard);
        boolean isValidSpacesLeft = true;
        int increment = 81;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j ++) {
                increment -= 1;
                setBoard[i][j] = new HashMap<>();
                setBoard[i][j].put(testerBoard.getSolutionBoard()[i][j], false);
                testerBoard.setBoard(setBoard);
                if (testerBoard.spacesLeft() != increment) {
                    isValidSpacesLeft = false;
                }
            }
        }
        assertTrue(isValidSpacesLeft);
    }

    @Test
    public void testMakeMove() {
        boolean allMovesAreValid = true;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if (currBoard[i][j].isEmpty()) {
                    hardBoard.makeMove(i, j, solutionBoard[i][j]);
                    currBoard = hardBoard.getCurrBoard();
                    int value = 0;
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        value = entry.getKey();
                    }
                    if (value != solutionBoard[i][j]) {
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
