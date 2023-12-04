package entity.board;

import java.util.HashMap;

/**
 * Scrambler class for a 4x4 sudoku board (EasyBoard).
 */
public class EasySudokuScrambler implements SudokuScrambler {
    private EasyBoard currBoard;

    /**
     * Initializes a new EasySudokuScrambler object.
     * @param currBoard is an EasyBoard object, that represents the current state of the board.
     */
    public EasySudokuScrambler(EasyBoard currBoard) {
        this.currBoard = currBoard;
    }

    /**
     * Updates the currBoard with a new EasyBoard object.
     * @param currBoard is a Board object
     */
    public void updateBoard(Board currBoard) {
        this.currBoard = (EasyBoard) currBoard;
    }

    /**
     * Scrambles the current EasyBoard.
     * The function generates a new randomized solutionBoard, and replaces the old values
     * of the previous EasyBoard with select values from the new solutionBoard. This ensures
     * that the new scrambled board is valid, and also that the positions from the old board
     * (including the new position filled in by the user) are maintained.
     * @return a new EasyBoard object, which is the new scrambled board
     */
    public EasyBoard scramble() {
        HashMap<Integer, Boolean>[][] currBoardValues = currBoard.getCurrBoard();
        EasyBoard newBoard = new EasyBoard();
        int[][] newBoardSolutions = newBoard.getSolutionBoard();
        HashMap<Integer, Boolean>[][] newBoardValues = newBoard.generateBlankBoard();
        int x = -1; int y = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!currBoardValues[i][j].isEmpty()) {
                    newBoardValues[i][j].put(newBoardSolutions[i][j], false);
                } else if (x == -1 && y == -1) {
                    x = i; y = j;
                    newBoardValues[i][j].put(newBoardSolutions[i][j], false);
                }
            }
        }
        newBoard.setBoard(newBoardValues);
        return newBoard;
    }

}