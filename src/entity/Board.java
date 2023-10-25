package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private ArrayList<ArrayList<HashMap<Integer, Boolean>>> currBoard;
    /* A matrix representing the board.
    - The Integer value is the value stored in that position.
    - The Boolean value is True when the value is a user stored value.
    Example on an Easy board:
    currBoard = [[{}, {2 = false}, {}, {4 = false}],
                [{}, {}, {3 = true}, {}],
                [{}, {3 = true}, {}, {1 = true}],
                [{1 = false}, {}, {2 = false}, {}]]
    See http://bit.ly/3tNbWNg for what this board would look like.
     */

    public Board(int difficulty) {
        if (difficulty == 1) {
            this.currBoard = this.generateEasyBoard();
        } else {
            this.currBoard = this.generateHardBoard();
        }
    }

    private ArrayList<ArrayList<HashMap<Integer, Boolean>>> generateEasyBoard() {
        /* TODO: return an Arraylist of values that generates a new Easy board.
            This board will have a 4 x 4 grid.
         */
        return new ArrayList<ArrayList<HashMap<Integer, Boolean>>>();
    }

    private ArrayList<ArrayList<HashMap<Integer, Boolean>>> generateHardBoard() {
        /* TODO: return an Arraylist of values that generates a new Hard board.
            This board will have a 9 x 9 grid.
         */
        return new ArrayList<ArrayList<HashMap<Integer, Boolean>>>();
    }

    public Board makeMove(char x, int y, int move) {
        /* TODO: this function stores the user's current move into the board,
            then sends an updated board to the GameState.
            - x is the x-coordinate of the user's move
            - y is the y-coordinate of the user's move
            - move is the integer value of the user's move
         */
        return this;
    }

    public boolean noSpacesLeft() {
        for (ArrayList<HashMap<Integer, Boolean>> row : currBoard) {
            for (HashMap<Integer, Boolean> value : row) {
                if (value.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
