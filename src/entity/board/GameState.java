package entity.board;

import java.util. *;

/**
 * A class representing the overall state of the game.
 */
public class GameState {
    private final int difficulty; // 1 - easy, 2 - hard
    private final SudokuScrambler sudokuScrambler;
    private Board currBoard;
    private int lives;

    private LinkedList<GameState> past_states;

    /**
     * Initializes a new GameState object.
     * If difficulty is set to 1, then an EasyBoard object is generated, along with an
     * EasySudokuScrambler object. If difficulty is set to 2, then a HardBoard object is
     * generated, along with a HardSudokuScrambler object.
     * This function also initializes lives to 5, and generates an empty LinkedList to
     * store past states.
     * @param difficulty is either 1 (easy) or 2 (hard)
     */
    public GameState(int difficulty) {
        this.difficulty = difficulty;
        if (difficulty == 1) {
            currBoard = new EasyBoard();
            sudokuScrambler = new EasySudokuScrambler((EasyBoard) currBoard);
        } else {
            currBoard = new HardBoard();
            sudokuScrambler = new HardSudokuScrambler((HardBoard) currBoard);
        }
        this.lives = 5;
        this.past_states = new LinkedList<>();
    }

    /**
     * Alternate initializer for a new GameState object.
     * The parameter past_states is a collection of the previous GameStates.
     * If difficulty is set to 1, then an EasyBoard object is generated, along with an
     * EasySudokuScrambler object. If difficulty is set to 2, then a HardBoard object is
     * generated, along with a HardSudokuScrambler object.
     * This function also initializes lives to 5.
     * @param difficulty is either 1 (easy) or 2 (hard)
     * @param past_states is a non-empty LinkedList of past GameStates
     */
    // akunna: overloading the GameState constructor so that it can pass in a linked list for the previous GameStates
    // seen when necessary
    public GameState(int difficulty, LinkedList<GameState> past_states) {
        this.difficulty = difficulty;
        if (difficulty == 1) {
            currBoard = new EasyBoard();
            sudokuScrambler = new EasySudokuScrambler((EasyBoard) currBoard);
        } else {
            currBoard = new HardBoard();
            sudokuScrambler = new HardSudokuScrambler((HardBoard) currBoard);
        }
        this.lives = 5;
        this.past_states = past_states;
    }

    /**
     * Makes a move based on the user input.
     * @param x column value (x coordinate) for user input
     * @param y row value (y coordinate) for user input
     * @param move user input value
     * @return a Board object of the current board
     */
    public Board makeMove(int x, int y, int move) {
        currBoard = currBoard.makeMove(x, y, move);
        return currBoard;
    }

    public boolean correctMove(int row, int column, int move) {
        return currBoard.correctMove(row, column, move);
    }

    /**
     * Updates the currBoard attribute in the sudoKuScrambler,
     * then scrambles the board by calling on the sudokuScrambler object.
     */
    public Board scrambleBoard() {
        sudokuScrambler.updateBoard(currBoard);
        Board newBoard = sudokuScrambler.scramble();
        currBoard.setBoard(newBoard.getCurrBoard());

        return currBoard;
    }

    /**
     * Subtracts 1 from the number of lives.
     */
    public void loseLife(){
        lives -= 1;
    }

    /**
     * @return true if the game is over (i.e. there are no more spaces left on the board).
     */
    public boolean gameOver() {
        return currBoard.noSpacesLeft();
    }

    public int spacesLeft() {return currBoard.spacesLeft();}

    /* ----- Getters and setters ----- */
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
    public void setCurrBoard(Board board) {
        this.currBoard = board;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int life) {this.lives = life;}

    public LinkedList<GameState> getPastStates() {return this.past_states; }

    public void setPastStates(LinkedList<GameState> past_states) {
        this.past_states = past_states;
    }

    /* ----- toString() methods ----- */
    // TODO: Figure out which of these to delete later
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

