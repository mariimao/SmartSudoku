package use_case.make_move;

import java.io.IOException;
import java.util.HashMap;

public interface MakeMoveBoardDataAccessInterface {
    int [][] convertToIntArray(HashMap<Integer, Boolean>[][]  currBoard);

    HashMap<Integer, Boolean>[][] convertToHashMap (int [][]  currBoard);

    int[][] generateBoard(int number_correct_moves) throws IOException;

    String generateSolution(int [][] board) throws IOException;

    Boolean verifyBoard(int[][] board) throws IOException;
}
