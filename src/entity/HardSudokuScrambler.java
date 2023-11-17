package entity;

import java.util.HashMap;

public class HardSudokuScrambler implements SudokuScrambler{
    private HardBoard currBoard;

    public HardSudokuScrambler(HardBoard currBoard) {
        this.currBoard = currBoard;
    }

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