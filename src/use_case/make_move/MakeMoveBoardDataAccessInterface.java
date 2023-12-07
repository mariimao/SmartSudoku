package use_case.make_move;

import java.io.IOException;
import java.util.HashMap;

/**
 * Interface for the data access object of the MakeMove use case. Implemented by SudokuDAO.
 */
public interface MakeMoveBoardDataAccessInterface {

    /**
     * Converts the current board into an int array. This is ONLY for hard board.
     *
     * @param currBoard is a nested array representation of the current board
     * @return a 2-dimensional array
     */
    int[][] convertToIntArray(HashMap<Integer, Boolean>[][] currBoard);

    /**
     * Converts the current board into a hashmap. This is ONLY for hard board.
     *
     * @param currBoard is a nested array representation of the current board
     * @return a hashmap of the numbers and if they are empty or not
     */
    HashMap<Integer, Boolean>[][] convertToHashMap(int[][] currBoard);

    /**
     * Generates a new board with n number of additional square prefilled
     *
     * @param number_correct_moves the number of moves to fill
     * @return a 2-dimensional array representing the board
     */
    int[][] generateBoard(int number_correct_moves) throws IOException;

    /**
     * generates the solutions using the sudoku api
     *
     * @param board the current board
     * @return a string representing the solutions
     */
    String generateSolution(int[][] board) throws IOException;

    /**
     * verifies if the board is solvable and follows the rules
     *
     * @param board the current board
     * @return true if it is valid or not
     */
    Boolean verifyBoard(int[][] board) throws IOException;
}
