package entity;
import java.util. *;

public class GameState {
    public static void main(String[] args) {
        //TODO: DELETE MAIN, just for testing
        EasyBoard testBoard = new EasyBoard("003T00004T004T01F2T00");
        GameState testState = new GameState(1);
        System.out.println(testState.currBoard);
        System.out.println(testState.toStringPause());
    }

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

    public void setCurrBoard(String values) {
        if (difficulty == 1) {this.currBoard = new EasyBoard(values);}
        else {this.currBoard = new HardBoard(values);}
    }

    public int getLives() {
        return this.lives;
    }

    public LinkedList<GameState> getPastStates() {return this.past_states; }

    public String toStringPause() {
        // should return everything needed to create an exact replica of this state (not counting the past states)
        // board representation-difficulty-lives
        // EXAMPLE: 003T00004T004T01F2T00-1-4  should be returned for board below if the player has only 4 lives
        // currBoard =    [
        //                [{},          {2 = false}, {},          {4 = false}],
        //                [{},          {},          {3 = true},  {}],
        //                [{},          {3 = true},  {},          {1 = true}],
        //                [{1 = false}, {},          {2 = false}, {}]
        //                ]
        if (this == null) {return "No Paused Game Exists";}
        if (this.currBoard == null) {return "No Board Exists for this Game State ";}
        StringJoiner representation = new StringJoiner("-");
        String boardRep = this.currBoard.toStringPause();
        representation.add(boardRep);
        representation.add(String.valueOf(difficulty));
        representation.add(String.valueOf(lives));

        return  representation.toString();
    }

}
