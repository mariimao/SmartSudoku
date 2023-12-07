package data_access;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpotifyDAOTest {

    private SpotifyDAO spotifyDAO;

    @Before
    public void init() {
        spotifyDAO = new SpotifyDAO();
    }

    @Test
    public void testGetClientId() {
        assertEquals("ba373bd1e8e44eecb52e192d0fbac238", spotifyDAO.getClientId());
    }

    @Test
    public void testGetClientSecret() {
        assertEquals("d99a71ede58b40179cf0946792c7123f", spotifyDAO.getClientSecret());
    }

    @Test
    public void testGetApiToken() {
        assertEquals("", spotifyDAO.getApiToken());
    }

    @Test
    public void testGetAccessCode() throws IOException {
        System.out.println(spotifyDAO.getAccessCode());
        assertFalse(spotifyDAO.getAccessCode().startsWith("BQBbLF1XlLhlhHHVsPo"));
    }

    @Test
    public void testCorrectArtistName() throws IOException {
        String artistID = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth - artist name
        String expectedArtist = "wave to earth";
        assertEquals(spotifyDAO.getArtistname(artistID), expectedArtist);
    }

    @Test
    public void testCorrectTrackID() throws IOException {
        // Tests to see if correct name is returned
        String trackID = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night - song name
        String expectedTrack = "A thought on an autumn night";
        assertEquals(spotifyDAO.getTrackName(trackID), expectedTrack);
    }

    @Test
    public void testCorrectDuration() throws IOException {
        // Tests to see if correct name is returned
        String trackID = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d";
        int expectedTime = 187000;
        assertEquals(spotifyDAO.getTrackDuration(trackID), expectedTime);
    }

    @Test
    public void testRefreshToken() {
        String token = spotifyDAO.getRefreshToken();
        String current_token = spotifyDAO.getApiToken();
        assertEquals(current_token, token);
    }

    @Test
    public void testSuggestions() throws IOException {
        ArrayList<String> suggestions = spotifyDAO.getSuggestions("watermelon sugar");
        // default amount is 20
        assertEquals(20, suggestions.size());
    }
}

