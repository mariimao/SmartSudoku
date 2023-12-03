package use_case.user_move;

import java.util.HashMap;

public interface UserMoveBoardDataAccessInterface {
    int[][] convertToIntArray(HashMap<Integer, Boolean>[][] currBoard);

    HashMap<Integer, Boolean>[][] convertToHashMap(int[][] currBoard);

    int[][] generateBoard(int number_correct_moves);

    String generateSolution(int[][] board);

    Boolean verifyBoard(int[][] board);

    String arrayToString(int[][] boardList);
}


