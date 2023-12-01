package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.startplayer.StartPlayerDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class SpotifyPlayer implements StartPlayerDataAccessInterface {

    private final String token;

    public SpotifyPlayer (SpotifyDAO spotifyDAO) {

        this.token = spotifyDAO.getApiToken();
    }

    public void play(String album_id, String song_id, String device) {
        // Just for testing api calling
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        String jsonBody = "{\n" +
                "    \"context_uri\": \"spotify:album:"+album_id+"\",\n" +
                "    \"offset\": {\n" +
                "        \"position\": 5 \n" +
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

    public void setVolume(int percent) {
        // uses can choose to change volume in game ?
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/volume?volume_percent="+percent) // might need to cast
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

    public ArrayList<String> getDevices() {
        // returns the possible devices that can be played on
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

            for (int i = 0; i < items.length(); i++ ) {
                JSONObject item = items.getJSONObject(i);
                String id = item.getString("id");
                devicesID.add(id);
            }
            return devicesID;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDevice(String deviceID) {
        // makes sure that music plays on correct device
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        String jsonBody = "{\n" +
                "    \"device_ids\": [\n" +
                "        \""+deviceID+"\"\n" +
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

    public static void main(String[] args) {
        // TESTING API CALLS

        String id = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth artist name
        String songid = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night
        String search = "bad idea";
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        spotifyDAO.getRefreshToken();
        SpotifyPlayer spotifyPlayer = new SpotifyPlayer(spotifyDAO);
        String device = spotifyPlayer.getDevices().get(0);
        System.out.println(spotifyPlayer.getDevices());
        String song_id = "1fOMMLeB2L150LYZ0s7TKP?si=ac8290c0385245d1";
        String album_id = "1NSS3nU2Nus4U8VPzGF8st?si=5SZTv1gESp2BZxIMjMUl9w";
        //spotifyPlayer.play(album_id, song_id, device);
        spotifyPlayer.pause(device);
    }
}
