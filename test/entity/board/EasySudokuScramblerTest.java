package entity.board;

import entity.board.EasyBoard;
import entity.board.EasySudokuScrambler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class EasySudokuScramblerTest {

    private EasyBoard easyBoard;
    private EasySudokuScrambler easySudokuScrambler;

    @Before
    public void init() {
        easyBoard = new EasyBoard();
        easySudokuScrambler = new EasySudokuScrambler(easyBoard);
    }

    @Test
    public void testValidBoardAfterScramble() {
        easySudokuScrambler.scramble();
        HashMap<Integer, Boolean>[][] currBoard = easyBoard.getCurrBoard();
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
    public void testValidSolutionsAfterScramble() {
        easySudokuScrambler.scramble();
        int[][] solutionBoard = easyBoard.getSolutionBoard();
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
    public void testScrambledBoardMatchesSolution() {
        easySudokuScrambler.scramble();
        HashMap<Integer, Boolean>[][] currBoard = easyBoard.getCurrBoard();
        boolean isValidScramble = true;
        int[][] solutionBoard = easyBoard.getSolutionBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!currBoard[i][j].isEmpty()) {
                    int value = 0;
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        value = entry.getKey();
                    }
                    if (value != solutionBoard[i][j]) {
                        isValidScramble = false;
                    }
                }
            }
        }
        assertTrue(isValidScramble);
    }
}
