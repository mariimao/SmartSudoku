package entity;

public interface Board {
    Board makeMove(char x, int y, int move);
    boolean noSpacesLeft();

    String toString();
}
