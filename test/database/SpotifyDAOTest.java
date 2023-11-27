package database;

import data_access.SpotifyDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpotifyDAOTest {

    private SpotifyDAO spotifyDAO;
    @Before
    public void init() {
        spotifyDAO = new SpotifyDAO();
    }

    @Test
    public void testCorrectArtistName() {
        // Tests to see if correct name is returned
        String artistID = "5069JTmv5ZDyPeZaCCXiCg?si=cb76yjogSJ6xYKQ0uyFcWA"; // wave to earth - artist name
        String expectedArtist = "wave to earth";
        assertEquals(spotifyDAO.getArtistname(artistID), expectedArtist);
    }

    @Test
    public void testCorrectTrackID() {
        // Tests to see if correct name is returned
        String trackID = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d"; // A thought on an autumn night - song name
        String expectedTrack = "A thought on an autumn night";
        assertEquals(spotifyDAO.getTrackName(trackID), expectedTrack);
    }

    @Test
    public void testCorrectDuration() {
        // Tests to see if correct name is returned
        String trackID = "4YaKlkNVJNbrIqN82EKFsQ?si=898dc4d49ee24c9d";
        int expectedTime = 187000;
        assertEquals(spotifyDAO.getTrackDuration(trackID), expectedTime);
    }

}
