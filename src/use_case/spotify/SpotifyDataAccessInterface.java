package use_case.spotify;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for the data access object of Spotify use cases. Implemented by UserDAO.
 */
public interface SpotifyDataAccessInterface {

    /**
     * @param search is the String search that represents what the user wants to search
     * @return an array of suggestions of songs based on search
     */
    ArrayList<String> getSuggestions(String search) throws IOException;

    /**
     * @param id the artist id on Spotify
     * @return the artist name based on the id
     */
    String getArtistname(String id) throws IOException;

    /**
     * @param id the track id on Spotify
     * @return the track name based on the id
     */
    String getTrackName(String id) throws IOException;
}
