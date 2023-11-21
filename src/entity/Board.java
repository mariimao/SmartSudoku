package entity;

public interface Board {
    Board makeMove(int x, int y, int move);
    boolean noSpacesLeft();
    int[][] generatePossibleValues();

    boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y);
    String toString();

    String toStringPause();
}
