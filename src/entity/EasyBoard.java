package entity;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class EasyBoard implements Board{
    public static void main(String[] args) {
        //TODO: DELETE MAIN, just for testing
        System.out.println(generateArrayFromString("02T04F003T003T01T1F02F0"));
        System.out.println(new EasyBoard());
        System.out.println(new EasyBoard("003T00004T004T01F2T00"));
    }
    private HashMap<Integer, Boolean>[][] currBoard;
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
    public EasyBoard() {
        this.currBoard = this.generateEasyBoard();
    }

    public EasyBoard(String positions) {this.currBoard = generateEasyBoard1(positions);}

    private HashMap<Integer, Boolean>[][] generateEasyBoard1(String str_positions) {
           /* This is a helper for the overloaded constructor for the EasyBoard class that will generate a board based
           on a complete set of positions.
          */
        // currBoard = [[{}, {2 = false}, {}, {4 = false}],
        //                [{}, {}, {3 = true}, {}],
        //                [{}, {3 = true}, {}, {1 = true}],
        //                [{1 = false}, {}, {2 = false}, {}]]
        // the string representation of currBoard should be:  "#2T#4F##3T##3T#1T1F#2F#"

        HashMap<Integer, Boolean>[][] easyBoard = blankEasyBoard();
        HashMap<Integer, Boolean> blankValue = new HashMap<>();
        String blankChar = "0"; // how we represent blank squares

        // populate the blank board with values based on str_positions
        int sidelength = 4; // length of the Sudoku Board
        return getHashMaps(str_positions, easyBoard, blankValue, blankChar, sidelength);
    }

    static HashMap<Integer, Boolean>[][] getHashMaps(String str_positions, HashMap<Integer, Boolean>[][] easyBoard, HashMap<Integer, Boolean> blankValue, String blankChar, int sidelength) {
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

    private HashMap<Integer, Boolean>[][] generateEasyBoard() {
        int[][] possibleValues = generatePossibleEasyBoardValues();
        // Delete this part later -----------
        String str = "Solution: \n";
        for (int z = 0; z <= 3; z++) {
            for (int w = 0; w <= 3; w++) {
                str += possibleValues[z][w];
            }
            str += "\n";
        }
        System.out.println(str);
        // -----------------------------------
        ArrayList<Integer> positions = generateEasyStartingPositions();
        HashMap<Integer, Boolean>[][] easyBoard = blankEasyBoard();
        int i = 0;
        for (int position : positions) {
            easyBoard[i][position].put(possibleValues[i][position], true);
            i++;
        }
        return easyBoard;
    }

    private int[][] generatePossibleEasyBoardValues() {
        int[][] possibleValues = new int[4][4];
        boolean badBoard = true;
        while (badBoard) {
            HashMap<int[][], Boolean> generatedValues = new HashMap<>();
            generatedValues = generatePossibleEasyBoardValuesHelper();
            Map.Entry<int[][], Boolean> entry = generatedValues.entrySet().iterator().next();
            badBoard = entry.getValue();
            int[][] generated = entry.getKey();
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    possibleValues[i][j] = generated[i][j];
                }
            }
        }
        return possibleValues;
    }
    private HashMap<int[][], Boolean> generatePossibleEasyBoardValuesHelper() {
        int[][] possibleValues = {{0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}};
        HashMap<int[][], Boolean> generatedValues = new HashMap<>();
        for (int i = 0; i <= 3; i++) {
            for(int j = 0; j <= 3; j++) {
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

    private HashMap<Integer, Boolean>[][] blankEasyBoard() {
        HashMap<Integer, Boolean>[][] blankEasyBoard = new HashMap[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                HashMap<Integer, Boolean> blankValue = new HashMap<>();
                blankEasyBoard[i][j] = blankValue;
            }
        }
        return blankEasyBoard;
    }

    private boolean valueNotAvailable(int[][] possibleValues, int value, int x, int y) {
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

    public EasyBoard makeMove(char x, int y, int move) {
        /* TODO: this function stores the user's current move into the board,
            then sends an updated board to the GameState.
            - x is the x-coordinate of the user's move
            - y is the y-coordinate of the user's move
            - move is the integer value of the user's move
         */
        return this;
    }

    public HashMap<Integer, Boolean>[][] getCurrBoard(){
        return currBoard;
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
}
