package entity;

public class GameState {

    private final int difficulty; // 1 - easy, 2 - hard
    private final SudokuAI sudokuAI;
    private Board currBoard;
    private int lives;

    public GameState(int difficulty) {
        this.difficulty = difficulty;
        this.sudokuAI = new SudokuAI(difficulty);
        if (difficulty == 1) {
            currBoard = new EasyBoard();
        } else {
            currBoard = new HardBoard();
        }
        this.lives = 5;
    }

    public void makeMove(char x, int y, int move) {
        currBoard = currBoard.makeMove(x, y, move);
    }

    public void scrambleBoard() {
        sudokuAI.scramble(currBoard);
    }

    public void loseLife(){
        lives -= 1;
    }

    public boolean gameOver() {
        return currBoard.noSpacesLeft();
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public Board getCurrBoard() {
        return this.currBoard;
    }

    public int getLives() {
        return this.lives;
    }

}
