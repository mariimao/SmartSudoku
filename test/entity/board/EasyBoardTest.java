package entity.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class EasyBoardTest {
    private EasyBoard easyBoard;
    private EasyBoard easyBoard2;
    private int[][] solutionBoard;
    private int[][] solutionBoard2;
    HashMap<Integer, Boolean>[][] currBoard;
    HashMap<Integer, Boolean>[][] currBoard2;

    @Before
    public void init() {
        easyBoard = new EasyBoardFactory().create();
        solutionBoard = easyBoard.getSolutionBoard();
        currBoard = easyBoard.getCurrBoard();
        String positions = "004T04T00003T00001T0";
        easyBoard2 = new EasyBoardFactory().create(positions);
        solutionBoard2 = easyBoard2.getSolutionBoard();
        currBoard2 = easyBoard2.getCurrBoard();
    }

    @Test
    public void testValidBoard() {
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
    public void testIsValidSolutions() {
        HashSet<String> seen = new HashSet<>();
        boolean isValidTestBoard = true;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                String b = "(" + solutionBoard[i][j] + ")";
                if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 2 + b + j / 2))
                    isValidTestBoard = false;
            }
        }
        assertTrue(isValidTestBoard);
    }

    @Test
    public void testGenerateBlankBoard() {
        boolean allBlankValues = true;
        HashMap<Integer, Boolean>[][] blankBoard = easyBoard.generateBlankBoard();
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                if (!blankBoard[i][j].isEmpty()) {
                    allBlankValues = false;
                    break;
                }
            }
        }
        assertTrue(allBlankValues);
    }

    @Test
    public void testCorrectMove() {
        boolean correctMoveIsValid = true;
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                if (!easyBoard.correctMove(i, j, solutionBoard[i][j])) {
                    correctMoveIsValid = false;
                }
            }
        }
        assertTrue(correctMoveIsValid);
    }

    @Test
    public void testSpacesLeft() {
        EasyBoard testerBoard = new EasyBoard();
        HashMap<Integer, Boolean>[][] setBoard = testerBoard.generateBlankBoard();
        testerBoard.setBoard(setBoard);
        boolean isValidSpacesLeft = true;
        int increment = 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j ++) {
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
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                if (currBoard[i][j].isEmpty()) {
                    easyBoard.makeMove(i, j, solutionBoard[i][j]);
                    currBoard = easyBoard.getCurrBoard();
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

        HashMap<Integer, Boolean>[][] fullBoard = new HashMap[4][4];
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                HashMap<Integer, Boolean> value = new HashMap<>();
                value.put(solutionBoard[i][j], true);
                fullBoard[i][j] = value;

                if (currBoard[i][j].isEmpty()) {
                    noSpacesLeft = false;
                }
            }
        }
        assertEquals(noSpacesLeft, easyBoard.noSpacesLeft());
        easyBoard.setBoard(fullBoard);
        assertTrue(easyBoard.noSpacesLeft());
    }
}
