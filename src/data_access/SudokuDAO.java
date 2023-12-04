package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.make_move.MakeMoveBoardDataAccessInterface;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SudokuDAO implements MakeMoveBoardDataAccessInterface {

    /**
     * Helper function for encoding board
     * @param board a 2-dimensional array of int
     * @return a string
     */
    private static String encodeBoardHelper(int[][] board) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j ++ ) {
                result.append(URLEncoder.encode(Integer.toString(board[i][j]), StandardCharsets.UTF_8));
                if (j < board[i].length - 1) {
                    result.append("%2C");
                }
            }
            if (i < board.length - 1) {
                result.append("%5D%2C%5B");
            }
        }
        return result.toString();
    }

    /**
     *
     * @param input
     * @return
     */
    private static String encoder(Map<String, int[][]> input) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, int[][]> entry: input.entrySet()) {
            String key = entry.getKey();
            int [][] board = entry.getValue();

            result.append(key).append("=").append("%5B%5B");
            result.append(encodeBoardHelper(board));
            result.append("%5D%5D");
        }
        return result.toString();
    }

    /**
     * converts the string to a 2-dimensional array
     * @param grid the string
     * @return a 2-dimensional array that represents the board
     */
    private static int[][] stringToArray(String grid) {
        int[][] boardlist = new int[9][9];
        char[] charBoardArray = grid.toCharArray();
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (char c : charBoardArray) {
            if (!(c == '[' || c == ']' || c == ',')) {
                newList.add(Character.getNumericValue(c));
            }
        }
        int k = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                int input = newList.get(k);
                boardlist[i][j] = input;
                k += 1;
            }
        }
        return boardlist;
    }

    /**
     * Converts the current board into an int array. This is ONLY for hard board.
     * @param currBoard
     * @return a 2-dimensional array
     */
    public int[][] convertToIntArray(HashMap<Integer, Boolean>[][]  currBoard) {
        int rows = 9;
        int cols = 9;

        int[][] convertedArray = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currBoard[i][j] != null && !currBoard[i][j].isEmpty()) {
                    int value = currBoard[i][j].keySet().iterator().next();
                    convertedArray[i][j] = value;
                } else {
                    convertedArray[i][j] = 0;
                }
            }
        }

        return convertedArray;
    }

    /**
     * Converts the current board into a hashmap. This is ONLY for hard board.
     * @param currBoard
     * @return a hashmap of the numbers and if they are empty or not
     */
    public HashMap<Integer, Boolean>[][] convertToHashMap (int [][]  currBoard) {
        // for hard board only
        int rows = 9;
        int cols = 9;

        HashMap<Integer, Boolean>[][] convertedHashmap;
        convertedHashmap = new HashMap[9][9];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currBoard[i][j] != 0) { // if spot isn't empty
                    HashMap<Integer, Boolean> value = new HashMap();
                    value.put(currBoard[i][j], false);
                    convertedHashmap[i][j] = value;
                }
            }
        }

        return convertedHashmap;
    }

    /**
     * Inputs a correct move into the current board
     * @param current_grid the current board
     * @param solution the current solution to the board
     * @param correct_moves the possible correct moves for the user
     * @return a new board with the correct move inside
     */
    private static int[][] insertCorrectMoves(int[][] current_grid,
                                              int[][] solution, int correct_moves) {

        int correct_counter = correct_moves;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (correct_counter > 0) {
                    if (current_grid[i][j] == 0) {
                        current_grid[i][j] = solution[i][j];
                        correct_counter -= 1;
                    }
                }
            }
        }

        return current_grid;

    }

    /**
     * Generates a new board with n number of additional square prefilled
     * @param number_correct_moves the number of moves to fill
     * @return a 2-dimensional array representing the board
     */
    public int[][] generateBoard(int number_correct_moves) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sudoku-api.vercel.app/api/dosuku?query={newboard(limit:1){grids{value}}}")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);
        JSONObject board = responseBody.getJSONObject("newboard");
        JSONObject grids = board.getJSONArray("grids").getJSONObject(0);
        JSONArray value = grids.getJSONArray("value");

        int[][] result = stringToArray(value.toString());
        int[][] solution = stringToArray(generateSolution(result));

        if (number_correct_moves == 0) {
            return result;
        }
        else {
            return insertCorrectMoves(result, solution, number_correct_moves);
        }
    }

    /**
     * generates the solutions using the sudoku api
     * @param board the current board
     * @return a string representing the solutions
     */
    public String generateSolution(int [][] board) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Map<String, int[][]> input = new HashMap<>();
        input.put("board", board);
        String testinput = encoder(input);
        RequestBody body = RequestBody.create(mediaType, testinput);
        Request request = new Request.Builder()
                .url("https://sugoku.onrender.com/solve")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString); // error happens at this line

        return responseBody.getJSONArray("solution").toString();
    }

    /**
     * verifies if the board is solvable and follows the rules
     * @param board the current board
     * @return true if it is valid or not
     */
    public Boolean verifyBoard(int[][] board) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Map<String, int[][]> input = new HashMap<>();
        input.put("board", board);
        String testinput = encoder(input);
        RequestBody body = RequestBody.create(mediaType, testinput) ;
        //RequestBody body = RequestBody.create(mediaType, Arrays.toString(test));
        Request request = new Request.Builder()
                .url("https://sugoku.onrender.com/solve")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString); // error happens at this line

        if (responseBody.get("status").equals("unsolvable")) {
            // means input is false
            return false;
        }
        return true;

    }


}
