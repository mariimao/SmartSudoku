package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.play_music.PlayMusicDataAccessInterface;
import use_case.spotify.SpotifyDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

/**
 * A data access object for spotify making use of the spotify API
 */
public class SpotifyDAO implements SpotifyDataAccessInterface, PlayMusicDataAccessInterface {

    private static final String CLIENT_ID = "ba373bd1e8e44eecb52e192d0fbac238";
    private static final String CLIENT_SECRET = "d99a71ede58b40179cf0946792c7123f";
    private final String client_id;
    private final String client_secret;
    private final String refresh_token;
    private String current_token;

    /**
     * Initializes a new SpotifyDAO object.
     */
    public SpotifyDAO() {
        this.client_id = CLIENT_ID;
        this.client_secret = CLIENT_SECRET;
        this.current_token = "";
        this.refresh_token = "AQDkEZahVWGy-Hamvdi6NXFqLGQQPXSDmELmRGEZnISrfqUkZH4D8Rw11cp3P--ShzWS_e2yA-TZS8fXJTzGekELnkIdWUNN8K3i931y_Exo0aBdR7R18CVDMeKvvR33uO4";
    }

    /**
     * @return the client id
     */
    String getClientId() {
        return this.client_id;
    }

    /**
     * @return the client secret
     */
    String getClientSecret() {
        return this.client_secret;
    }

    /**
     * @return the api token
     */
    public String getApiToken() {
        return this.current_token;
    }

    /**
     * @return returns the access code
     */
    public String requestAuthorization() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String scope = "app-remote-control streaming user-read-playback-state";
        String jsonBody = "https://accounts.spotify.com/authorize" +
                "?response_type=code" +
                "&client_id=" + client_id +
                "&scope=" + scope +
                "&redirect_uri=http://localhost:8888/callback";

        Request request = new Request.Builder()
                .url(jsonBody)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String responseString = "{" + response.body().string();
        JSONObject responseBody = new JSONObject(responseString);
        return responseBody.getString("token");
    }

    /**
     * @return refresh token
     */
    public String getRefreshToken() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String credentials = this.client_id + ":" + this.client_secret;
        String convertedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        String jsonBody = "grant_type=refresh_token&refresh_token=" + refresh_token;

        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic " + convertedCredentials)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // error happens at this line
            this.current_token = responseBody.getString("access_token");
            return responseBody.getString("access_token");

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the access code
     */
    public String getAccessCode() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String jsonBody = "grant_type=client_credentials&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString); // error happens at this line

        return responseBody.getString("access_token");
    }

    /**
     * @param id the song identification
     * @return returns the song artists name
     */
    public String getArtistname(String id) throws IOException {
        // Just for testing api calling
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);
        String name = responseBody.getString("name");

        return name;
    }

    /**
     * @param id the song identification
     * @return returns the track name
     */
    public String getTrackName(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);

        // gets the name of the track based on ID
        String name = responseBody.getString("name");

        return name;

    }

    /**
     * @param id the song identification
     * @return returns the albumID
     */
    public String getAlbumID(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // System.out.println(access_token);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);
        JSONObject album = responseBody.getJSONObject("album");


        // gets the name of the track based on ID
        String name = album.getString("id");

        return name;

    }

    /**
     * @param id the song identification
     * @return returns position the track has in the album
     */
    public Integer getTrackPosition(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);


        // gets the name of the track based on ID
        int name = responseBody.getInt("track_number");

        return name;

    }

    /**
     * @param id the song identification
     * @return returns the track duration
     */
    public int getTrackDuration(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + id)
                .addHeader("Authorization", "Bearer " + (access_token))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseBody = new JSONObject(responseString);

        // gets the duration in milliseconds
        int duration = responseBody.getInt("duration_ms");

        return duration;
    }

    /**
     * @param search the search phrase
     * @return a list of song ids
     */
    public ArrayList<String> getSuggestions(String search) throws IOException {
        // should return a list of id suggestions
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String access_token = getAccessCode();
        // currently only searching by type
        String input = "https://api.spotify.com/v1/search?q=remaster%2520track%3A" + search + "%2520artist%3A&type=track&market=US&include_external=audio";

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

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String id = item.getString("id");
                songs.add(id);
                // checking if it is the correct id
                assert (Objects.equals(getTrackName(id), item.getString("name")));
            }
            return songs;


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}