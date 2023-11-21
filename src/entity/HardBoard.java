/*
    TODO: All of the code in this file is incomplete/broken. Please standby.
 */

package entity;

import java.util.*;

import static entity.EasyBoard.getHashMaps;

public class HardBoard implements Board {
    private HashMap<Integer, Boolean>[][] currBoard;
    private final Random random = new Random();

    public HardBoard() {
        this.currBoard = this.generateHardBoard();
    }

    public HardBoard(String positions) {this.currBoard = generateHardBoard1(positions);}


    private HashMap<Integer, Boolean>[][] generateHardBoard1(String str_positions) {
        HashMap<Integer, Boolean>[][] hardBoard = blankHardBoard();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();
        String blankChar = "0"; // how we represent blank squares

        // populate the blank board with values based on str_positions
        int sidelength = 9; // length of the Sudoku Board
        return getHashMaps(str_positions, hardBoard, blankValue, blankChar, sidelength);
    }


    private HashMap<Integer, Boolean>[][] generateHardBoard() {
        /* TODO: return an Arraylist of values that generates a new Hard board.
            This board will have a 9 x 9 grid.
            This is the syntax for generating random numbers in python:
            random.nextInt((max - min) + 1) + min;
         */
        int[][] possibleValues = generatePossibleValues();
        // Delete this part later -----------
        String str = "Solution: \n";
        for (int z = 0; z <= 8; z++) {
            for (int w = 0; w <= 8; w++) {
                str += possibleValues[z][w];
            }
            str += "\n";
        }
        System.out.println(str);
        // -----------------------------------
        int[][] positions = generateHardStartingPositions();
        HashMap<Integer, Boolean>[][] hardBoard = blankHardBoard();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (positions[i][j] == 1) {
                    hardBoard[i][j].put(possibleValues[i][j], true);
                }
            }
        }
        return hardBoard;
    }

    public int[][] generatePossibleValues() {
        int[][] possibleValues = new int[9][9];
        boolean badBoard = true;
        while (badBoard) {
            HashMap<int[][], Boolean> generatedValues = new HashMap<>();
            generatedValues = generatePossibleHardBoardValuesHelper();
            Map.Entry<int[][], Boolean> entry = generatedValues.entrySet().iterator().next();
            badBoard = entry.getValue();
            int[][] generated = entry.getKey();
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    possibleValues[i][j] = generated[i][j];
                }
            }
        }
        return possibleValues;
    }

    private HashMap<int[][], Boolean> generatePossibleHardBoardValuesHelper() {
        int[][] possibleValues = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        HashMap<int[][], Boolean> generatedValues = new HashMap<>();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                int value = (int) (Math.random() * 9) + 1;
                int tries = 0;
                while (tries <= 100 && valueNotAvailable(possibleValues, value, i, j)) {
                    value = (int) (Math.random() * 9) + 1;
                    tries++;
                }
                if (tries > 100) {
                    generatedValues.put(new int[9][9], true);
                    return generatedValues;
                } else {
                    possibleValues[i][j] = value;
                }
            }
        }
        generatedValues.put(possibleValues, false);
        return generatedValues;
    }

    private HashMap<Integer, Boolean>[][] blankHardBoard() {
        HashMap<Integer, Boolean>[][] blankHardBoard = new HashMap[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                HashMap<Integer, Boolean> blankValue = new HashMap<>();
                blankHardBoard[i][j] = blankValue;
            }
        }
        return blankHardBoard;
    }

    public boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y) {
        // Checking if the row is okay
        for (int item : possibleValues[x]) {
            if (value == item) {
                return true;
            }
        }
        // Checking if the column is okay
        for (int i = 0; i <= 8; i++) {
            if (value == possibleValues[i][y]) {
                return true;
            }
        }
        // Checking if the square is okay
        int sqt = (int) Math.sqrt(9);
        int boxRowSt = x - x % sqt;
        int boxColSt = y - y % sqt;
        for (int r1 = boxRowSt; r1 < boxRowSt + sqt; r1++) {
            for (int d = boxColSt; d < boxColSt + sqt; d++) {
                if (possibleValues[r1][d] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] generateHardStartingPositions() {
        int[][] startingPositions = new int[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                int rand = (int) (Math.random() * 2);
                startingPositions[i][j] = rand;
            }
        }
        return startingPositions;
    }

    public HardBoard makeMove(int x, int y, int move) {
        /* TODO: this function stores the user's current move into the board,
            then sends an updated board to the GameState.
            - x is the x-coordinate of the user's move
            - y is the y-coordinate of the user's move
            - move is the integer value of the user's move
         */
        HashMap<Integer, Boolean> value = new HashMap<>();
        value.put(move, true);
        this.currBoard[y][x] = (value);
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

    @Override
    public String toString() {
        String str = "";
        for (HashMap<Integer, Boolean>[] row : currBoard) {
            for (HashMap<Integer, Boolean> map : row) {
                if (map.isEmpty()) {
                    str += "0 ";
                } else {
                    for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
                        str += entry.getKey() + " ";
                    }
                }
            }
            str += "\n";
        }
        return str;
    }

    public String toStringPause() {
        // return board in the format found in the overridden constructor method
        // boardlength should be 4 for easy and 9 for hard
        int boardlength = 9;
        StringBuilder values = new StringBuilder();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();


        for (int row = 0; row < boardlength; row++) {
            for (int col = 0; col < boardlength; col++) {
                HashMap<Integer, Boolean> position = currBoard[row][col];
                if (position.isEmpty()) {
                    values.append('0');
                } else {
                    int int_value = position.keySet().iterator().next();
                    Boolean truth_value = position.get(int_value);
                    String value = String.valueOf(int_value);
                    if (truth_value) {
                        value = value.concat("T");
                    } else {
                        value = value.concat("F");
                    }
                    values.append(value);
                }
            }
        }

        return values.toString();
    }
}
