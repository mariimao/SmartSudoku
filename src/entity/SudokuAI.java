package entity;

public class SudokuAI {

    private final int difficulty;

    public SudokuAI(int difficulty) {
        this.difficulty = difficulty;
    }

    public Board scramble(Board board) {
        if (difficulty == 1) {
            return scrambleEasy(board);
        } else {
            return scrambleHard(board);
        }
    }

    private Board scrambleEasy(Board board) {
        /* TODO: implement a function that scrambles the numbers on an Easy board.
         */
        return board;
    }

    private Board scrambleHard(Board board) {
        /* TODO: implement a function that scrambles the numbers on a Hard board.
         */
        return board;
    }
}
