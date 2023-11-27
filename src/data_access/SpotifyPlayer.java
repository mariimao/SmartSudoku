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

        this.token = "BQCcl0NLK3rQEjcSnKmkXsGooWVOKCGIsQyHKfU9QB5mvSvETTukZtB9EKSIV-t6ftM3awXGdxCEdJeNpCIMmro5IBex3aR3tmHVhyUpF7Pl_S1BmQvJbrUlFo2HcyI2FIDaGbf9a2V-gBZHFyMhAisJzcaRH4c4J_m6DecUunwPwAxtbJOotYosyOw";
    }

    // should database be a attribute of the player
//    public void setToken() {
//        getAccessCode();
//    }

    public void play(String id) {
        // Just for testing api calling
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        String jsonBody = "'{\n" +
                "    \"context_uri\": \"spotify:album:track:4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d\",\n" +
                "    \"offset\": {\n" +
                "        \"position\": 5\n" +
                "    },\n" +
                "    \"position_ms\": 0\n" +
                "}'";
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/play?device_id=1dcaf4a91fd95baa4e919d0de916814c385a8ff3")
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

    public void pause() {
        // pauses current song playing
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Testing String
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/pause")
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
//        System.out.println(spotifyDAO.getAccessCode());
//        System.out.println(spotifyDAO.getArtistname(id));
//        System.out.println(spotifyDAO.getTrackDuration(songid));
//        System.out.println(spotifyDAO.getTrackName(songid));
//        System.out.println(spotifyDAO.getSuggestions(search));
        SpotifyPlayer spotifyPlayer = new SpotifyPlayer(spotifyDAO);
        //spotifyPlayer.requestAuthorization();
        System.out.println(spotifyPlayer.getDevices());
        spotifyPlayer.play("4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d");
        //spotifyPlayer.play("4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d");

    }
}
