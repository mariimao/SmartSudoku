package entity.board;

/**
 * Interface for the SudokuScrambler objects. Implemented by EasySudokuScrambler and HardSudokuScrambler.
 */
public interface SudokuScrambler {

    /**
     * Updates the current board.
     * @param currBoard is a Board object
     */
    void updateBoard(Board currBoard);

    /**
     * Scrambles the current board.
     * @return new scrambled Board object
     */
    Board scramble();
}