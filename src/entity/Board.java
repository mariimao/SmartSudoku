package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private ArrayList<ArrayList<HashMap<Integer, Boolean>>> currBoard;

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
        for (ArrayList row : currBoard) {
            for (Object value : row) {
                if (((HashMap<Integer, Boolean>) value).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
