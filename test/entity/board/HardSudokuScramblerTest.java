import entity.board.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class HardSudokuScramblerTest implements SudokuScramblerTest {

    private HardBoard hardBoard;
    private HardSudokuScrambler hardSudokuScrambler;

    @Before
    public void init() {
        hardBoard = new HardBoard();
        hardSudokuScrambler = new HardSudokuScrambler(hardBoard);
    }

    @Test
    public void testValidBoardAfterScramble() {
        hardSudokuScrambler.scramble();
        HashMap<Integer, Boolean>[][] currBoard = hardBoard.getCurrBoard();
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
    public void testValidSolutionsAfterScramble() {
        hardSudokuScrambler.scramble();
        int[][] solutionBoard = hardBoard.getSolutionBoard();
        HashSet<String> seen = new HashSet<>();
        boolean isValidTestBoard = true;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                String b = "(" + solutionBoard[i][j] + ")";
                if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                    isValidTestBoard = false;
            }
        }
        assertTrue(isValidTestBoard);
    }

    @Test
    public void testScrambledBoardMatchesSolution() {
        hardSudokuScrambler.scramble();
        HashMap<Integer, Boolean>[][] currBoard = hardBoard.getCurrBoard();
        boolean isValidScramble = true;
        int[][] solutionBoard = hardBoard.getSolutionBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
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
