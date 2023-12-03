package use_case.make_move;

import java.util.HashMap;

public interface MakeMoveBoardDataAccessInterface {
    int [][] convertToIntArray(HashMap<Integer, Boolean>[][]  currBoard);

    HashMap<Integer, Boolean>[][] convertToHashMap (int [][]  currBoard);

    int[][] generateBoard(int number_correct_moves);

    String generateSolution(int [][] board);

    Boolean verifyBoard(int[][] board);
}
