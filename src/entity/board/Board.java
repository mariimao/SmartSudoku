package entity.board;

import java.util.ArrayList;
import java.util.HashMap;

public interface Board {
    Board makeMove(int x, int y, int move);
    boolean noSpacesLeft();
    boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y);
    int[][] generatePossibleValues();
    String toString();
    void setBoard(HashMap<Integer, Boolean>[][] newBoard);
    HashMap<Integer, Boolean>[][] getCurrBoard();
    int[][] getSolutionBoard();
    HashMap<Integer, Boolean>[][] generateBlankBoard();

    String toStringPause();

    ArrayList toArray();
}
