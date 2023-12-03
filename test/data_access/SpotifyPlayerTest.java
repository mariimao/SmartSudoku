package data_access;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;


public class SpotifyPlayerTest {

    private SpotifyPlayer spotifyPlayer;
    private SpotifyDAO spotifyDAO;

    @Before
    public void init() {
        this.spotifyDAO = new SpotifyDAO();
        this.spotifyPlayer = new SpotifyPlayer(spotifyDAO);
    }

    @Test
    public void testGetDevice() throws IOException {
        String id = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth artist name
        String songid = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night
        String search = "bad idea";
        SpotifyPlayer spotifyPlayer = new SpotifyPlayer(spotifyDAO);
        ArrayList<String> device = spotifyPlayer.getDevices();
        System.out.println(device);
        assert device.isEmpty();
    }
}
