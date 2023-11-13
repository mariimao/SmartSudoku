package entity;

public interface Board {
    Board makeMove(int x, int y, int move);
    boolean noSpacesLeft();

    String toString();

    String toStringPause();
}
