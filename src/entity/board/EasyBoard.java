package entity.board;

import java.util.*;

/**
 * Class representing a 4x4 sudoku puzzle.
 */
public class EasyBoard implements Board {
    private HashMap<Integer, Boolean>[][] currBoard;
    /* A matrix representing the board.
    - The Integer value is the value stored in that position.
    - The Boolean value is True when the value is a user stored value.
    Example on an Easy board:
    currBoard = [[{}, {2 = false}, {}, {4 = false}],
                [{}, {}, {3 = true}, {}],
                [{}, {3 = true}, {}, {1 = true}],
                [{1 = false}, {}, {2 = false}, {}]]
    See http://bit.ly/3tNbWNg for an example of what this board would look like.
     */
    private int[][] solutionBoard;

    /**
     * Default constructor for EasyBoard object.
     * Initializes solutionBoard to a full board of possible values, and currBoard.
     */
    public EasyBoard() {
        this.solutionBoard = generatePossibleValues();
        this.currBoard = generateEasyBoard();
    }

    /**
     * Alternative constructor for EasyBoard object.
     * Initializes the currBoard from a string of given positions.
     * @param positions a string representing a valid board
     */
    public EasyBoard(String positions) {
        this.currBoard = generateEasyBoard(positions);
        int[][] board = positionsToArray(positions);
        solve(board); // solves the solutionBoard
        this.solutionBoard = board;
    }

    /**
     * Called in the initial constructor of EasyBoard.
     * @return a HashMap representation of a random 4x4 sudoku board
     */
    private HashMap<Integer, Boolean>[][] generateEasyBoard() {
        int[][] possibleValues = solutionBoard;
        ArrayList<Integer> positions = generateEasyStartingPositions();
        HashMap<Integer, Boolean>[][] easyBoard = generateBlankBoard();
        int i = 0;
        for (int position : positions) {
            easyBoard[i][position].put(possibleValues[i][position], false);
            i++;
        }
        return easyBoard;
    }

    /**
     * Called in the alternative constructor of EasyBoard.
     * @param str_positions positions that the board is to be generated to
     * @return HashMap representation of the newly generated board
     */
    private HashMap<Integer, Boolean>[][] generateEasyBoard(String str_positions) {
        // If currBoard is formatted like this:
        // currBoard = [[{}, {2 = false}, {}, {4 = false}],
        //                [{}, {}, {3 = true}, {}],
        //                [{}, {3 = true}, {}, {1 = true}],
        //                [{1 = false}, {}, {2 = false}, {}]]
        // the string representation of currBoard should be: "02F04F003T01T1F02F0"

        HashMap<Integer, Boolean>[][] easyBoard = generateBlankBoard();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();
        String blankChar = "0"; // how we represent blank squares

        // populate the blank board with values based on str_positions
        int sidelength = 4; // length of the Sudoku Board
        return getHashMaps(str_positions, easyBoard, blankValue, blankChar, sidelength);
    }

    /**
     * This function generates a complete, randomized 4x4 sudoku board.
     * It is called in the initial constructor of EasyBoard, to initialize solutionBoard.
     * @return a nested array of values for a completed 4x4 sudoku board
     */
    public int[][] generatePossibleValues() {
        int[][] possibleValues = new int[4][4];
        boolean badBoard = true;
        while (badBoard) {
            HashMap<int[][], Boolean> generatedValues;
            generatedValues = generatePossibleEasyBoardValuesHelper();
            Map.Entry<int[][], Boolean> entry = generatedValues.entrySet().iterator().next();
            badBoard = entry.getValue();
            int[][] generated = entry.getKey();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    possibleValues[i][j] = generated[i][j];
                }
            }
        }
        return possibleValues;
    }

    /**
     * Helper method for generatePossibleValues().
     * This function randomly generates numbers from 1 to 4, and attempts to put them
     * into the sudoku grid. If the value is invalid (i.e. it can't be put in that position)
     * then it tries again.
     * @return a HashMap of the generated board, and whether the board is invalid
     */
    private HashMap<int[][], Boolean> generatePossibleEasyBoardValuesHelper() {
        int[][] possibleValues = {{0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}};
        HashMap<int[][], Boolean> generatedValues = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = (int) (Math.random() * 4) + 1;
                int tries = 0;
                while (tries <= 50 && valueNotAvailable(possibleValues, value, i, j)) {
                    value = (int) (Math.random() * 4) + 1;
                    tries ++;
                }
                if (tries > 50) {
                    generatedValues.put(new int[4][4], true);
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
     * by checking the row, column, and corresponding 2x2 square.
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
        for (int i = 0; i <= 3; i++){
            if (value == possibleValues[i][y]) {
                return true;
            }
        }
        // Checking if the square is okay
        int sqt = (int) Math.sqrt(4);
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
     * @return Hashmap template for a blank 4x4 sudoku board
     */
    public HashMap<Integer, Boolean>[][] generateBlankBoard() {
        HashMap<Integer, Boolean>[][] blankEasyBoard = new HashMap[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                HashMap<Integer, Boolean> blankValue = new HashMap<>();
                blankEasyBoard[i][j] = blankValue;
            }
        }
        return blankEasyBoard;
    }

    /**
     * Helper method for generateEasyBoard().
     * Returns the starting positions for the computer generated values to be
     * presented to the user. Each row will have one random square out of four selected
     * for the value to be placed. This method prevents scenarios for when 3 or more squares
     * in the same column are chosen.
     * In the return value, the index of the ArrayList corresponds to the row, and the value
     * corresponds to the index of the column chosen.
     * @return ArrayList of starting positions
     */
    private ArrayList<Integer> generateEasyStartingPositions() {
        int[][] possiblePositions = {{0, 1, 2, 3},
                                    {0, 1, 2, 3},
                                    {0, 1, 2, 3},
                                    {0, 1, 2, 3}};
        int rowAValue = 0; int rowBValue = 0; int rowCValue = 0; int rowDValue = 0;
        while (threeOrMoreEqual(rowAValue, rowBValue, rowCValue, rowDValue)) {
            rowAValue = (int) (Math.random() * 4);
            rowBValue = (int) (Math.random() * 4);
            rowCValue = (int) (Math.random() * 4);
            rowDValue = (int) (Math.random() * 4);
        }
        ArrayList<Integer> startingPositions = new ArrayList<>();
        startingPositions.add(possiblePositions[0][rowAValue]);
        startingPositions.add(possiblePositions[1][rowBValue]);
        startingPositions.add(possiblePositions[2][rowCValue]);
        startingPositions.add(possiblePositions[3][rowDValue]);
        return startingPositions;
    }

    /**
     * Helper function for generateEasyStartingPositions().
     * Returns false if there are less than 3 squares in the same column chosen
     * as the starting positions. This ensures that the player board will not have more
     * than 2 computer generated values in the same column.
     * @param a column position of the value in the first row
     * @param b column position of the value in the second row
     * @param c column position of the value in the third row
     * @param d column position of the value in the fourth row
     * @return false if the positions are valid
     */
    private boolean threeOrMoreEqual(int a, int b, int c, int d) {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(a); values.add(b); values.add(c); values.add(d);
        for (int value : values) {
            int occurrences = Collections.frequency(values, value);
            if (occurrences >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method for the alternative version of generateEasyBoard.
     * @param str_positions string of positions
     * @param easyBoard blank EasyBoard object
     * @param blankValue blank HashMap object
     * @param blankChar the char "0"
     * @param sidelength the int 4
     * @return a HashMap representation of the board based on the given positions
     */
    private HashMap<Integer, Boolean>[][] getHashMaps(String str_positions, HashMap<Integer, Boolean>[][] easyBoard, HashMap<Integer, Boolean> blankValue, String blankChar, int sidelength) {
        String info; // corresponding information based on str_positions

        for (int row = 0; row < sidelength; row++) {
            for (int col = 0; col < sidelength; col++) {
                if (String.valueOf(str_positions.charAt(0)).equals(blankChar)) {
                    easyBoard[row][col] = blankValue;
                    str_positions = str_positions.substring(1);
                }
                else {
                    info =  str_positions.substring(0,2);
                    str_positions = str_positions.substring(2);
                    int int_value = Integer.parseInt(info.substring(0,1));
                    boolean truth_value = info.charAt(1) == 'T';
                    HashMap<Integer, Boolean> value = new HashMap<>();
                    value.put(int_value, truth_value);
                    easyBoard[row][col] = value;
                }
            }
        }
        return easyBoard;
    }

    /**
     * Helper function for the alternative EasyBoard constructor.
     * @param positions String of desired values
     * @return a nested array of the 4x4 sudoku board based on the desired values
     */
    private int[][] positionsToArray(String positions) {
        int[][] valueArray = new int[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
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
     * @param board current state of the 4x4 board
     * @return true if the board has been solved
     */
    private boolean solve(int[][] board) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (board[x][y] == 0) {
                    for (int k = 1; k <= 4; k++) {
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
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (board[i][j] != 0) {
                    int value = board[i][j];
                    String b = "(" + value + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 2 + b + j / 2))
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
     * @param column is the x-coordinate of the user's move
     * @param move is the integer value of the user's move
     * @return an updated EasyBoard
     */
    public EasyBoard makeMove(int row, int column, int move) {
        HashMap<Integer, Boolean> value = new HashMap<>();
        value.put(move, true);
        this.currBoard[row][column] = value;
        return this;
    }

    /**
     * Updates the solution board.
     */
    public void updateSolutionBoard() {
        int[][] arrayBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                HashMap<Integer, Boolean> position = currBoard[row][col];
                if (position == null || position.isEmpty()) {
                    spacesCount++;
            }
        }
    }
        return spacesCount;
    }

    /* ----- Getters and setters ----- */
    public HashMap<Integer, Boolean>[][] getCurrBoard(){
        return currBoard;
    }

    public void setBoard(HashMap<Integer, Boolean>[][] newBoard) {
        this.currBoard = newBoard;
        int[][] arrayBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (newBoard[i][j].isEmpty()) {
                    arrayBoard[i][j] = 0;
                }
                else {
                    for (Map.Entry<Integer, Boolean> entry : newBoard[i][j].entrySet()) {
                        arrayBoard[i][j] = entry.getKey();
                    }
                }
            }
        }
        solve(arrayBoard);
        solutionBoard = arrayBoard;
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
        StringBuilder values = new StringBuilder();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();
        int boardlength = 4;


        for (int row = 0; row < boardlength; row++) {
            for (int col = 0; col < boardlength; col++) {
                HashMap<Integer, Boolean> position = currBoard[row][col];
                if(position.isEmpty()) {values.append('0');}
                else{
                    int int_value = position.keySet().iterator().next();
                    Boolean truth_value = position.get(int_value);
                    String value = String.valueOf(int_value);
                    if (truth_value) {value = value.concat("T");}
                    else {value = value.concat("F");}
                    values.append(value);
                }
            }
        }

        return values.toString();
    }

    public ArrayList<Integer> toArray() {
        // returns a string interpretation of the EasyBoard
        int sidelength = 4;
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

    private static ArrayList<String> generateArrayFromString(String str_positions) {
        // creates an array based on the strings such that each index corresponds to a specific positions info
        ArrayList<String> positions = new ArrayList<>();

        String info = "";
        for (char character : str_positions.toCharArray()) {
            info = info.concat(String.valueOf(character));
            if (info.equals("0")) {
                positions.add(info);
                info = "";
                continue;
            }
            if (info.length() == 2) {
                positions.add(info);
                info = "";
            }
        }
        positions.add(info);
        return positions;
    }
}
