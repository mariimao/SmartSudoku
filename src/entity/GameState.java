package entity;
import java.util. *;

public class GameState {

    private final int difficulty; // 1 - easy, 2 - hard
    private final SudokuAI sudokuAI;
    private Board currBoard;
    private int lives;

    private LinkedList<GameState> past_states;

    public GameState(int difficulty) {
        this.difficulty = difficulty;
        this.sudokuAI = new SudokuAI(difficulty);
        if (difficulty == 1) {
            currBoard = new EasyBoard();
        } else {
            currBoard = new HardBoard();
        }
        this.lives = 5;
        this.past_states = new LinkedList<>();
    }

    // akunna: overloading the GameState constructor so that it can pass in a linked list for the previous GameStates
    // seen when necessary
    public GameState(int difficulty, LinkedList<GameState> past_states) {
        this.difficulty = difficulty;
        this.sudokuAI = new SudokuAI(difficulty);
        if (difficulty == 1) {
            currBoard = new EasyBoard();
        } else {
            currBoard = new HardBoard();
        }
        this.lives = 5;

        this.past_states = past_states;
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

    public LinkedList<GameState> getPastStates() {return this.past_states; }

}
