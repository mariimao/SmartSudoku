package data_access;

import okhttp3.*;
import org.json.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SpotifyDAO {


    private static final String API_URL = "https://accounts.spotify.com/api/token";
    // load API_TOKEN from env variable.
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private static final String CLIENT_ID = "696d348cba294681b77d2dd77768750d";
    private static final String CLIENT_SECRET = "57025d9e9f6442229e9c2a8b3f7c5f6b";
    private final String client_id;
    private final String client_secret;

    public SpotifyDAO() {
        this.client_id = CLIENT_ID;
        this.client_secret = CLIENT_SECRET;
    }

    public String getClientId() {
        return this.client_id;
    }

    public String getClientSecret() {
        return this.client_secret;
    }

    public String getAccessCode() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String jsonBody = "grant_type=client_credentials&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET;
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // error happens at this line

            return responseBody.getString("access_token");

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getArtistname(String id) {
        // Just for testing api calling
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);
            String name = responseBody.getString("name");

            return name;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTrackName(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);

            // gets the name of the track based on ID
            String name = responseBody.getString("name");

            return name;


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTrackDuration(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);

            // gets the duration in milliseconds
            int duration = responseBody.getInt("duration_ms");

            return duration;


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getSuggestions(String search) {
        // should return a list of id suggestions
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // currently only searching by type
        String input = "https://api.spotify.com/v1/search?q=remaster%2520track%3A"+search+"%2520artist%3A&type=track&market=US&include_external=audio";

        Request request = new Request.Builder()
                .url(input)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        try {
            ArrayList<String> songs = new ArrayList<>();

            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);
            JSONObject tracks = responseBody.getJSONObject("tracks");
            JSONArray items = tracks.getJSONArray("items");

            for (int i = 0; i < items.length(); i++ ) {
                JSONObject item = items.getJSONObject(i);
                String id = item.getString("id");
                songs.add(id);
                // checking if it is the correct id
                assert(Objects.equals(getTrackName(id), item.getString("name")));
            }
            return songs;


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPlayback(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);

            // gets the duration in milliseconds
            int duration = responseBody.getInt("duration_ms");



        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        // TESTING API CALLS
        String id = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth artist name
        String songid = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night
        String search = "bad idea";
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        System.out.println(spotifyDAO.getArtistname(id));
        System.out.println(spotifyDAO.getTrackDuration(songid));
        System.out.println(spotifyDAO.getTrackName(songid));
        System.out.println(spotifyDAO.getSuggestions(search));

    }

}