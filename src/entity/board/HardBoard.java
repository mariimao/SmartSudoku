package entity.board;

import java.util.*;

/**
 * Class representing a 9x9 sudoku puzzle.
 */
public class HardBoard implements Board {
    private HashMap<Integer, Boolean>[][] currBoard;
    private int[][] solutionBoard;


    /**
     * Default constructor for HardBoard object.
     * Initializes solutionBoard to a full board of possible values, and currBoard.
     */
    public HardBoard() {
        this.solutionBoard = generatePossibleValues();
        this.currBoard = generateHardBoard();
    }

    /**
     * Alternative constructor for HardBoard object.
     * Initializes the currBoard from a string of given positions.
     * @param positions a string representing a valid board
     */
    public HardBoard(String positions) {
        this.currBoard = generateHardBoard(positions);
        int[][] board = positionsToArray(positions);
        solve(board); // solves the solutionBoard
        this.solutionBoard = board;
    }

    /**
     * Called in the initial constructor of HardBoard.
     * @return a HashMap representation of a random 9x9 sudoku board
     */
    private HashMap<Integer, Boolean>[][] generateHardBoard() {
        int[][] possibleValues = solutionBoard;
        // TODO: Delete this part later -----------
        String str = "Solution: \n";
        for (int z = 0; z < 9; z++) {
            for (int w = 0; w < 9; w++) {
                str += possibleValues[z][w];
            }
            str += "\n";
        }
        System.out.println(str);
        // -----------------------------------
        int[][] positions = generateHardStartingPositions();
        HashMap<Integer, Boolean>[][] hardBoard = generateBlankBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (positions[i][j] == 1) {
                    hardBoard[i][j].put(possibleValues[i][j], false);
                }
            }
        }
        return hardBoard;
    }

    /**
     * Called in the alternative constructor of HardBoard.
     * @param str_positions positions that the board is to be generated to
     * @return HashMap representation of the newly generated board
     */
    private HashMap<Integer, Boolean>[][] generateHardBoard(String str_positions) {
        HashMap<Integer, Boolean>[][] hardBoard = generateBlankBoard();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();
        String blankChar = "0"; // how we represent blank squares

        // populate the blank board with values based on str_positions
        int sidelength = 9; // length of the Sudoku Board
        return getHashMaps(str_positions, hardBoard, blankValue, blankChar, sidelength);
    }


    /**
     * This function generates a complete, randomized 9x9 sudoku board.
     * It is called in the initial constructor of HardBoard, to initialize solutionBoard.
     * @return a nested array of values for a completed 9x9 sudoku board
     */
    public int[][] generatePossibleValues() {
        int[][] possibleValues = new int[9][9];
        boolean badBoard = true;
        while (badBoard) {
            HashMap<int[][], Boolean> generatedValues = new HashMap<>();
            generatedValues = generatePossibleHardBoardValuesHelper();
            Map.Entry<int[][], Boolean> entry = generatedValues.entrySet().iterator().next();
            badBoard = entry.getValue();
            int[][] generated = entry.getKey();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    possibleValues[i][j] = generated[i][j];
                }
            }
        }
        return possibleValues;
    }

    /**
     * Helper method for generatePossibleValues().
     * This function randomly generates numbers from 1 to 9, and attempts to put them
     * into the sudoku grid. If the value is invalid (i.e. it can't be put in that position)
     * then it tries again.
     * @return a HashMap of the generated board, and whether the board is invalid
     */
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

    /**
     * A helper method for generatePossibleEasyBoardValuesHelper().
     * Checks if a value can be placed in the grid while maintaining a valid grid
     * by checking the row, column, and corresponding 4x4 square.
     * @param possibleValues nested array of the currently generated board
     * @param value value to be placed down
     * @param x row coordinate of where the value is to be placed
     * @param y column coordinate of where the value is to be placed
     * @return true if the value cannot be placed in that location
     */
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

    /**
     * @return Hashmap template for a blank 9x9 sudoku board
     */
    public HashMap<Integer, Boolean>[][] generateBlankBoard() {
        HashMap<Integer, Boolean>[][] blankHardBoard = new HashMap[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                HashMap<Integer, Boolean> blankValue = new HashMap<>();
                blankHardBoard[i][j] = blankValue;
            }
        }
        return blankHardBoard;
    }

    /**
     * Helper method for generateHardBoard().
     * Returns the starting positions for the computer generated values to be
     * presented to the user. Each square has a 50% chance of being chosen as a starting
     * position. If it is chosen to be a starting position, the value at that index is 1.
     * If it is chosen to be empty, the value at that index is 0.
     * @return Array of starting positions
     */
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

    /**
     * Helper method for the alternative version of generateHardBoard.
     * @param str_positions string of positions
     * @param hardBoard blank HardBoard object
     * @param blankValue blank HashMap object
     * @param blankChar the char "0"
     * @param sidelength the int 9
     * @return a HashMap representation of the board based on the given positions
     */
    private HashMap<Integer, Boolean>[][] getHashMaps(String str_positions, HashMap<Integer, Boolean>[][] hardBoard, HashMap<Integer, Boolean> blankValue, String blankChar, int sidelength) {
        String info; // corresponding information based on str_positions

        for (int row = 0; row < sidelength; row++) {
            for (int col = 0; col < sidelength; col++) {
                if (String.valueOf(str_positions.charAt(0)).equals(blankChar)) {
                    hardBoard[row][col] = blankValue;
                    str_positions = str_positions.substring(1);
                }
                else {
                    info =  str_positions.substring(0,2);
                    str_positions = str_positions.substring(2);
                    int int_value = Integer.parseInt(info.substring(0,1));
                    boolean truth_value = info.charAt(1) == 'T';
                    HashMap<Integer, Boolean> value = new HashMap<>();
                    value.put(int_value, truth_value);
                    hardBoard[row][col] = value;
                }
            }
        }
        return hardBoard;
    }

    /**
     * Helper function for the alternative HardBoard constructor.
     * @param positions String of desired values
     * @return a nested array of the 9x9 sudoku board based on the desired values
     */
    private int[][] positionsToArray(String positions) {
        int[][] valueArray = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (String.valueOf(positions.charAt(0)).equals("0")) {
                    valueArray[row][col] = 0;
                    positions = positions.substring(1);
                }
                else {
                    String info =  positions.substring(0,2);
                    positions = positions.substring(2);
                    int int_value = Integer.parseInt(info.substring(0,1));
                    valueArray[row][col] = int_value;
                }
            }
        }
        return valueArray;
    }

    /**
     * Solves the sudoku board recursively based on a nested array of inputs.
     * @param board current state of the 9x9 board
     * @return true if the board has been solved
     */
    private boolean solve(int[][] board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        board[x][y] = k;
                        if (isValid(board) && solve(board)) {
                            return true;
                        }
                        board[x][y] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper function for solve(). Checks if the values in a 4x4 sudoku
     * board makes the board valid.
     * @param board nested array of values for the board
     * @return true if the board is valid
     */
    private boolean isValid(int[][] board) {
        HashSet<String> seen = new HashSet<>();
        boolean isValidBoard = true;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != 0) {
                    int value = board[i][j];
                    String b = "(" + value + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        isValidBoard = false;
                }
            }
        }
        return isValidBoard;
    }

    /**
     * This function stores the user's current move into the board, then sends an
     * updated board to the GameState.
     * @param row is the y-coordinate of the user's move
     * @param col is the x-coordinate of the user's move
     * @param move is the integer value of the user's move
     * @return an updated HardBoard
     */
    public HardBoard makeMove(int row, int col, int move) {
        HashMap<Integer, Boolean> value = new HashMap<>();
        value.put(move, true);
        this.currBoard[row][col] = (value);
        return this;
    }

    /**
     * Updates the solution board.
     */
    public void updateSolutionBoard() {
        int[][] arrayBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (currBoard[i][j].isEmpty()) {
                    arrayBoard[i][j] = 0;
                }
                else {
                    for (Map.Entry<Integer, Boolean> entry : currBoard[i][j].entrySet()) {
                        arrayBoard[i][j] = entry.getKey();
                    }
                }
            }
        }
        solve(arrayBoard);
        solutionBoard = arrayBoard;
    }

    /**
     * Checks if the user placed a correct move on the board.
     * @param row int object representing the index of the row
     * @param column int object representing the index of the column
     * @param move int object representing the value of the user move
     * @return true if the move was correct (i.e. matches the solution)
     */
    public boolean correctMove(int row, int column, int move) {
        return solutionBoard[row][column] == move;
    }

    /**
     * Checks if a board has been completely filled.
     * @return true if there are no spaces left
     */
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

    public int spacesLeft() {
        int spacesCount = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                HashMap<Integer, Boolean> position = currBoard[row][col];
                if (position == null || position.isEmpty()) {
                    spacesCount++;
                }
            }
        }

        return spacesCount;
    }

    /* ----- Getters and setters ----- */
    public HashMap<Integer, Boolean>[][] getCurrBoard() {
        return this.currBoard;
    }

    public void setBoard(HashMap<Integer, Boolean>[][] newBoard) {
        this.currBoard = newBoard;
    }

    public int[][] getSolutionBoard() {
        return solutionBoard;
    }

    /* ----- toString() methods ----- */
    // TODO: Figure out which of these to delete later

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

    public ArrayList<Integer> toArray() {
        // returns an array interpretation of the EasyBoard
        int sidelength = 9;
        ArrayList<Integer> values = new ArrayList<>();

        for (int row = 0; row < sidelength; row++) {
            for (int col = 0; col < sidelength; col++) {
                HashMap<Integer, Boolean> position = currBoard[row][col];
                if (position.isEmpty()) {values.add(0);}
                else {values.add(position.keySet().iterator().next());} // assuming the size of each position's hashmap is 1
            }
        }
        return values;
    }

    // if we end up using valueNotAvailable, this helps convert Board into int[][] for possibleValues
//    public int[][] convertToIntArray(HashMap<Integer, Boolean>[][]  currBoard) {
//        int rows = 4;
//        int cols = 4;
//
//        int[][] convertedArray = new int[rows][cols];
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (currBoard[i][j] != null && !currBoard[i][j].isEmpty()) {
//                    int value = currBoard[i][j].keySet().iterator().next();
//                    convertedArray[i][j] = value;
//                } else {
//                    convertedArray[i][j] = 0;
//                }
//            }
//        }
//
//        return convertedArray;
//    }

}
