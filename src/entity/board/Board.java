package entity.board;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for the Board class.
 * Represents a sudoku board object. Current classes that implement this are EasyBoard
 * and HardBoard.
 */
public interface Board {

    /**
     * This function generates a complete, randomized sudoku board.
     * It is called in the initial constructor of a Board object, to initialize solutionBoard.
     * @return a nested array of values for a completed sudoku board
     */
    int[][] generatePossibleValues();

    /**
     * Checks if a value can be placed in the grid while maintaining a valid grid
     * by checking the row, column, and corresponding square.
     * @param possibleValues nested array of the currently generated board
     * @param value value to be placed down
     * @param x row coordinate of where the value is to be placed
     * @param y column coordinate of where the value is to be placed
     * @return true if the value cannot be placed in that location
     */
    boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y);

    /**
     * @return Hashmap template for a blank sudoku board
     */
    HashMap<Integer, Boolean>[][] generateBlankBoard();

    /**
     * This function stores the user's current move into the board, then sends an
     * updated board to the GameState.
     * @param x is the x-coordinate of the user's move
     * @param y is the y-coordinate of the user's move
     * @param move is the integer value of the user's move
     * @return an updated EasyBoard
     */
    Board makeMove(int x, int y, int move);

    /**
     * Checks if the user placed a correct move on the board.
     * @param row int object representing the index of the row
     * @param column int object representing the index of the column
     * @param move int object representing the value of the user move
     * @return true if the move was correct (i.e. matches the solution)
     */
    boolean correctMove(int row, int column, int move);

    /**
     * Checks if a board has been completely filled.
     * @return true if there are no spaces left
     */
    boolean noSpacesLeft();

    /* ----- Getters and setters ----- */
    void setBoard(HashMap<Integer, Boolean>[][] newBoard);
    HashMap<Integer, Boolean>[][] getCurrBoard();
    int[][] getSolutionBoard();


//    int[][] convertToIntArray(HashMap<Integer, Boolean>[][] currBoard);

    /* ----- toString() methods ----- */
    // TODO: Figure out which of these to delete later
    String toString();
    String toStringPause();
    ArrayList toArray();
}
