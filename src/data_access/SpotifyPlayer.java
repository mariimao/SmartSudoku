package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.startplayer.StartPlayerDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A data access object for getting the playback of spotify songs
 */
public class SpotifyPlayer implements StartPlayerDataAccessInterface {

    private final String token;

    /**
     * Constructor for spotify player
     *
     * @param spotifyDAO is the spotify data access object, it gets song data
     */
    public SpotifyPlayer(SpotifyDAO spotifyDAO) {

        this.token = spotifyDAO.getApiToken();
    }

    /**
     * Plays the music
     *
     * @param album_id the album identification
     * @param position position in the album
     * @param device   the device the song will play on
     */
    public void play(String album_id, String position, String device) {
        // Just for testing api calling
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        String jsonBody = "{\n" +
                "    \"context_uri\": \"spotify:album:" + album_id + "\",\n" +
                "    \"offset\": {\n" +
                "        \"position\": " + position + "\n" +
                "    },\n" +
                "    \"position_ms\": 0\n" +
                "}";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/play?device_id=" + device)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + (this.token))
                .put(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // testing purposes

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pauses current song playing
     *
     * @param device the device the song is playing on
     */
    public void pause(String device) {
        // pauses current song playing
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), "");
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/pause?device_id=" + device)
                .addHeader("Authorization", "Bearer " + (this.token))
                .put(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // testing purposes

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Changes the volume
     *
     * @param percent the percentage for volume.
     */
    public void setVolume(int percent) {
        // uses can choose to change volume in game ?
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/volume?volume_percent=" + percent) // might need to cast
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + (this.token))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); // testing purposes

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return an arraylist of strings that contain possible devices that can be played on
     */
    public ArrayList<String> getDevices() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/devices") // might need to cast
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + (this.token))
                .build();
        try {
            ArrayList<String> devicesID = new ArrayList<>();

            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString);
            JSONArray items = responseBody.getJSONArray("devices");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String id = item.getString("id");
                devicesID.add(id);
            }
            return devicesID;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the device tha the song is played on
     *
     * @param deviceID the device identification
     */
    public void setDevice(String deviceID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        String jsonBody = "{\n" +
                "    \"device_ids\": [\n" +
                "        \"" + deviceID + "\"\n" +
                "    ]\n" +
                "}'";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + (this.token))
                .put(requestBody)
                .build();
        try {
            ArrayList<String> devicesID = new ArrayList<>();

            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); //Testing Purposes

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the current device identification
     */
    public String getCurrentDevice() {
        // checks which DEVICE is currently playing
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player")
                .addHeader("Authorization", "Bearer " + (this.token))
                .build();
        try {

            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            JSONObject responseBody = new JSONObject(responseString); //Testing Purposes
            JSONObject deviceInfo = responseBody.getJSONObject("device");
            String deviceID = deviceInfo.getString("id");

            return deviceID;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
