package data_access;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

            return responseBody.toString();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public String verifyBoard(int[][] board) {
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

            return responseBody.getString("status");

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // TESTING API CALLS
        SudokuDAO sudokuDAO = new SudokuDAO();
        //System.out.println(sudokuDAO.verifyBoard());
    }


}
