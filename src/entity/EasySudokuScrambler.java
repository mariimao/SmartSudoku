package entity;

import java.util.HashMap;
import java.util.Map;

public class EasySudokuScrambler implements SudokuScrambler {
    private EasyBoard currBoard;

    public EasySudokuScrambler(EasyBoard currBoard) {
        this.currBoard = currBoard;
    }

    public EasyBoard scramble() {
        HashMap<Integer, Boolean>[][] currBoardValues = currBoard.getCurrBoard();
        EasyBoard newBoard = new EasyBoard();
        int[][] newBoardSolutions = newBoard.getSolutionBoard();
        HashMap<Integer, Boolean>[][] newBoardValues = newBoard.generateBlankBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!currBoardValues[i][j].isEmpty()) {
                    newBoardValues[i][j].put(newBoardSolutions[i][j], true);
                }
            }
        }
        newBoard.setBoard(newBoardValues);
        return newBoard;
    }

}
