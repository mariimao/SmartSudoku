package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import use_case.user_move.UserMoveBoardDataAccessInterface;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SudokuDAO {

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

    private static String encodeHelper(Map<String, int[][]> input) {
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

    private static Boolean testEncoder(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if (s1.length() != s2.length()) {
            return false;
        }
        int i = 0;
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        for (i = 0; i < s1.length(); i++) {
            if (charArray1[i] != charArray2[i]) {
                char a = charArray1[i];
                char b = charArray2[i];
                return false;
            }
        }
        return true;
    }

    private static int[][] stringToArray(String grid) {
        int[][] boardlist = new int[9][9];
        char[] charBoardArray = grid.toCharArray();
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (char c : charBoardArray) {
            if (!(c == '[' || c == ']' || c == ',')) {
                newList.add(Character.getNumericValue(c));
            }
        }
        int i = 0;
        int j = 0;
        int k = 0;
        for (i = 0; i < 9; i++){
            for (j = 0; j < 9; j++) {
                int input = newList.get(k);
                boardlist[i][j] = input;
                k += 1;
            }
        }
        return boardlist;
    }

    public int[][] convertToIntArray(HashMap<Integer, Boolean>[][]  currBoard) {
        // for hard board only
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

    public HashMap<Integer, Boolean>[][] convertToHashMap (int [][]  currBoard) {
        // for hard board only
        int rows = 9;
        int cols = 9;

        HashMap<Integer, Boolean>[][] convertedHashmap;
        convertedHashmap = new HashMap[9][9];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currBoard[i][j] != 0) { // if spot isnt empty
                    HashMap<Integer, Boolean> value = new HashMap();
                    value.put(currBoard[i][j], false);
                    convertedHashmap[i][j] = value;
//                } else {
//                    convertedArray[i][j] = 0;
                }
            }
        }

        return convertedHashmap;
    }

    private static int[][] insertCorrectMoves(int[][] current_grid,
                                              int[][] solution, int correct_moves) {
        int i = 0;
        int j = 0;
        int correct_counter = correct_moves;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
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

    public int[][] generateBoard(int number_correct_moves) {
        // generates a new board with n number of additional square prefilled
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sudoku-api.vercel.app/api/dosuku?query={newboard(limit:1){grids{value}}}")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);
            JSONObject board = responseBody.getJSONObject("newboard");
            JSONObject grids = board.getJSONArray("grids").getJSONObject(0);
            JSONArray value = grids.getJSONArray("value");

            int[][] result = stringToArray(value.toString());
            int[][] solution = stringToArray(generateSolution(result));

            //System.out.println(value.toString());

            if (number_correct_moves == 0) {
                return result;
            }
            else {
                return insertCorrectMoves(result, solution, number_correct_moves);
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateSolution(int [][] board) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Map<String, int[][]> input = new HashMap<>();
        input.put("board", board);
        String testinput = encodeHelper(input);
        RequestBody body = RequestBody.create(mediaType, testinput);
        Request request = new Request.Builder()
                .url("https://sugoku.onrender.com/solve")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // error happens at this line

            return responseBody.getJSONArray("solution").toString();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean verifyBoard(int[][] board) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Map<String, int[][]> input = new HashMap<>();
        input.put("board", board);
        String testinput = encodeHelper(input);
        RequestBody body = RequestBody.create(mediaType, testinput) ;
        //RequestBody body = RequestBody.create(mediaType, Arrays.toString(test));
        Request request = new Request.Builder()
                .url("https://sugoku.onrender.com/solve")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // error happens at this line

            if (responseBody.get("status").equals("unsolvable")) {
                // means input is false
                return false;
            }
            return true;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // TESTING API CALLS
        SudokuDAO sudokuDAO = new SudokuDAO();
        // should be true
        int [][] board = {
                {0,0,0,0,0,0,8,0,0},
                {0,0,4,0,0,8,0,0,9},
                {0,7,0,0,0,0,0,0,5},
                {0,1,0,0,7,5,0,0,8},
                {0,5,6,0,9,1,3,0,0},
                {7,8,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0},
                {0,0,0,9,3,0,0,1,0},
                {0,0,5,7,0,0,4,0,3}
        };
        //System.out.println(sudokuDAO.verifyBoard(board));
        //should be false
        int [][] board2 = {
                {0,0,0,0,0,0,8,8,0},
                {0,0,4,0,0,8,0,0,9},
                {0,7,0,0,0,0,0,0,5},
                {0,1,0,0,7,5,0,0,8},
                {0,5,6,0,9,1,3,0,0},
                {7,8,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0},
                {0,0,0,9,3,0,0,1,0},
                {0,0,5,7,0,0,4,0,3}
        };
        //System.out.println(sudokuDAO.verifyBoard(board2));
        System.out.println(sudokuDAO.generateBoard(2));
        //System.out.println(Arrays.deepToString(board2));
        //System.out.print(sudokuDAO.generateSolution(sudokuDAO.generateBoard(5)));
    }


}
