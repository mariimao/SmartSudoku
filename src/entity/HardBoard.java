/*
    TODO: All of the code in this file is incomplete/broken. Please standby.
 */

package entity;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class HardBoard implements Board {
    private HashMap<Integer, Boolean>[][] currBoard;
    private final Random random = new Random();

    public HardBoard() {
        this.currBoard = this.generateHardBoard();
    }

    private HashMap<Integer, Boolean>[][] generateHardBoard() {
        /* TODO: return an Arraylist of values that generates a new Hard board.
            This board will have a 9 x 9 grid.
            This is the syntax for generating random numbers in python:
            random.nextInt((max - min) + 1) + min;
         */
        int[][] possibleValues = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                int value = random.nextInt(9) + 1;
                while (valueNotAvailable(possibleValues, value, i, j)) {
                    value = random.nextInt(9) + 1;
                }
                possibleValues[i][j] = value;
            }
        }
        return new HashMap[0][0];
    }

    private HashMap<Integer, Boolean>[][] blankHardBoard() {
        HashMap<Integer, Boolean>[][] blankHardBoard = new HashMap[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                HashMap<Integer, Boolean> blankValue = new HashMap<>();
                blankHardBoard[i][j] = blankValue;
            }
        }
        return blankHardBoard;
    }

    private boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y) {
        // Checking if the row is okay
        for (int item : possibleValues[x]) {
            if (value == item) {
                return false;
            }
        }
        // Checking if the column is okay
        for (int item : possibleValues[y]) {
            if (value == item) {
                return false;
            }
        }
        // Checking if the square is okay
        if (x <= 2 && y <= 2) {
            return checkSquare(possibleValues, value, "A1");
        } else if (x >= 3 && x <= 5 && y <= 2) {
            return checkSquare(possibleValues, value, "A2");
        } else if (x >= 6 && y <= 2) {
            return checkSquare(possibleValues, value, "A3");
        } else if (x <= 2 && y <= 5) {
            return checkSquare(possibleValues, value, "B1");
        } else if (x >= 3 && x <= 5 && y <= 5) {
            return checkSquare(possibleValues, value, "B2");
        } else if (x >= 6 && y <= 5) {
            return checkSquare(possibleValues, value, "B3");
        } else if (x <= 2) {
            return checkSquare(possibleValues, value, "C1");
        } else if (x <= 5) {
            return checkSquare(possibleValues, value, "C2");
        } else {
            return checkSquare(possibleValues, value, "C3");
        }
    }

    private boolean checkSquare(int[][] possibleValues, int value, String square) {
        int xIndex = -1; int yIndex = -1;
        switch (square) {
            case "A1" -> {xIndex = 0; yIndex = 0;}
            case "A2" -> {xIndex = 3; yIndex = 0;}
            case "A3" -> {xIndex = 6; yIndex = 0;}
            case "B1" -> {xIndex = 0; yIndex = 3;}
            case "B2" -> {xIndex = 3; yIndex = 3;}
            case "B3" -> {xIndex = 6; yIndex = 3;}
            case "C1" -> {xIndex = 0; yIndex = 6;}
            case "C2" -> {xIndex = 3; yIndex = 6;}
            case "C3" -> {xIndex = 6; yIndex = 6;}
        }
        for (int i = xIndex; i <= xIndex + 2; i++) {
            for (int j = yIndex; j <= yIndex + 2; j++) {
                if (value == possibleValues[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public HardBoard makeMove(char x, int y, int move) {
        /* TODO: this function stores the user's current move into the board,
            then sends an updated board to the GameState.
            - x is the x-coordinate of the user's move
            - y is the y-coordinate of the user's move
            - move is the integer value of the user's move
         */
        return this;
    }

    public boolean noSpacesLeft() {
        for (HashMap<Integer, Boolean>[] row : currBoard) {
            for (HashMap<Integer, Boolean> value : row) {
                if (value.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public HashMap<Integer, Boolean>[][] getCurrBoard() {
        return this.currBoard;
    }
}
