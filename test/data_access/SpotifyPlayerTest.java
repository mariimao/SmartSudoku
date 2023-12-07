package data_access;

import entity.SpotifyPlayer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;


public class SpotifyPlayerTest {

    private SpotifyPlayer spotifyPlayer;

    @Before
    public void init() {
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        String token = spotifyDAO.getRefreshToken();
        this.spotifyPlayer = new SpotifyPlayer(token);
    }

    @Test
    public void testGetDevice() {
        ArrayList<String> device = spotifyPlayer.getDevices();
        assert device.isEmpty();
    }
}
