package entity.board;

import java.util.HashMap;

/**
 * Scrambler class for a 9x9 sudoku board (HardBoard).
 */
public class HardSudokuScrambler implements SudokuScrambler {
    private HardBoard currBoard;

    /**
     * Initializes a new EasySudokuScrambler object.
     *
     * @param currBoard is an EasyBoard object, that represents the current state of the board.
     */
    public HardSudokuScrambler(HardBoard currBoard) {
        this.currBoard = currBoard;
    }

    /**
     * Updates the currBoard with a new HardBoard object.
     *
     * @param currBoard is a Board object
     */
    public void updateBoard(Board currBoard) {
        this.currBoard = (HardBoard) currBoard;
    }

    /**
     * Scrambles the current HardBoard.
     * The function generates a new randomized solutionBoard, and replaces the old values
     * of the previous HardBoard with select values from the new solutionBoard. This ensures
     * that the new scrambled board is valid, and also that the positions from the old board
     * (including the new position filled in by the user) are maintained.
     *
     * @return a new HardBoard object, which is the new scrambled board
     */
    public HardBoard scramble() {
        HashMap<Integer, Boolean>[][] currBoardValues = currBoard.getCurrBoard();
        HardBoard newBoard = new HardBoard();
        int[][] newBoardSolutions = newBoard.getSolutionBoard();
        HashMap<Integer, Boolean>[][] newBoardValues = newBoard.generateBlankBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!currBoardValues[i][j].isEmpty()) {
                    newBoardValues[i][j].put(newBoardSolutions[i][j], true);
                }
            }
        }
        newBoard.setBoard(newBoardValues);
        return newBoard;
    }
}