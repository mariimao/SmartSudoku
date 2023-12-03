package database;

import data_access.SpotifyDAO;
import data_access.SpotifyPlayer;
import org.junit.Before;
import org.junit.Test;


public class SpotifyPlayerTest {

    private SpotifyPlayer spotifyPlayer;
    private SpotifyDAO spotifyDAO;

    @Before
    public void init() {
        this.spotifyDAO = new SpotifyDAO();
        this.spotifyPlayer = new SpotifyPlayer(spotifyDAO);
    }

    @Test
    public void getDeviceTest() {
        String id = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth artist name
        String songid = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night
        String search = "bad idea";
        spotifyDAO.getRefreshToken();
        SpotifyPlayer spotifyPlayer = new SpotifyPlayer(spotifyDAO);
        String device = spotifyPlayer.getDevices().get(0);
        assert device.equals("571991662d75442ec207b2c3ff59575f58fdf859");
    }
}
