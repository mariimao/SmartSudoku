package data_access;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SudokuDAOTest {

    private SudokuDAO sudokuDAO;

    @Before
    public void init() {
        sudokuDAO = new SudokuDAO();
    }

    @Test
    public void testCorrectSolution() throws IOException {
        // Tests to see if correct solution is generated
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 8, 0, 0},
                {0, 0, 4, 0, 0, 8, 0, 0, 9},
                {0, 7, 0, 0, 0, 0, 0, 0, 5},
                {0, 1, 0, 0, 7, 5, 0, 0, 8},
                {0, 5, 6, 0, 9, 1, 3, 0, 0},
                {7, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 9, 3, 0, 0, 1, 0},
                {0, 0, 5, 7, 0, 0, 4, 0, 3}
        };
        String expected = "[[1,9,2,3,5,7,8,4,6],[5,3,4,1,6,8,7,2,9],[6,7,8,2,4,9,1,3,5],[4,1,3,6,7,5,2,9,8],[2,5,6,8,9,1,3,7,4],[7,8,9,4,2,3,6,5,1],[3,2,1,5,8,4,9,6,7],[8,4,7,9,3,6,5,1,2],[9,6,5,7,1,2,4,8,3]]";
        String actual = sudokuDAO.generateSolution(board);
        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectVerificationTrue() throws IOException {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 8, 0, 0},
                {0, 0, 4, 0, 0, 8, 0, 0, 9},
                {0, 7, 0, 0, 0, 0, 0, 0, 5},
                {0, 1, 0, 0, 7, 5, 0, 0, 8},
                {0, 5, 6, 0, 9, 1, 3, 0, 0},
                {7, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 9, 3, 0, 0, 1, 0},
                {0, 0, 5, 7, 0, 0, 4, 0, 3}
        };
        Boolean expected = true;
        Boolean actual = sudokuDAO.verifyBoard(board);
        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectVerificationFalse() throws IOException {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 8, 8, 0},
                {0, 0, 4, 0, 0, 8, 0, 0, 9},
                {0, 7, 0, 0, 0, 0, 0, 0, 5},
                {0, 1, 0, 0, 7, 5, 0, 0, 8},
                {0, 5, 6, 0, 9, 1, 3, 0, 0},
                {7, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 9, 3, 0, 0, 1, 0},
                {0, 0, 5, 7, 0, 0, 4, 0, 3}
        };
        Boolean expected = false;
        Boolean actual = sudokuDAO.verifyBoard(board);
        assertEquals(expected, actual);
    }

    @Test
    public void testValidBoardGeneration() throws IOException {
        int[][] actual_board = sudokuDAO.generateBoard(3);
        Boolean actual = sudokuDAO.verifyBoard(actual_board);
        assert (actual);
    }

    @Test
    public void testBoardGenerationZero() throws IOException {
        int[][] actual_board = sudokuDAO.generateBoard(0);
        Boolean actual = sudokuDAO.verifyBoard(actual_board);
        assert (actual);
    }

    @Test
    public void testConvertToArraytoHashmap() throws IOException {
        int[][] expected_board = sudokuDAO.generateBoard(3);
        int[][] converted_board = new int[9][9];
        HashMap<Integer, Boolean>[][] convertedHashmap;

        convertedHashmap = sudokuDAO.convertToHashMap(expected_board);
        converted_board = sudokuDAO.convertToIntArray(convertedHashmap);

        assertEquals(expected_board, converted_board);
        assert (convertedHashmap.length == 9);

    }

    @Test
    public void testTestEncoder() {

    }
}
