package data_access;

import entity.SpotifyPlayer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;


public class SpotifyPlayerTest {

    private SpotifyPlayer spotifyPlayer;
    private String token;

    @Before
    public void init() {
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        String token = spotifyDAO.getRefreshToken();
        this.spotifyPlayer = new SpotifyPlayer(token);
    }

    @Test
    public void testGetDevice() throws IOException {
        String id = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth artist name
        String songid = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night
        String search = "bad idea";
        ArrayList<String> device = spotifyPlayer.getDevices();
        assert device.isEmpty();
    }
}
