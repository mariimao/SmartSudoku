package use_case.spotify;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for the data access object of Spotify use cases. Implemented by UserDAO.
 */
public interface SpotifyDataAccessInterface {

    /**
     * @return an array of suggestions of songs based on search
     * @param search is the String search that represents what the user wants to search
     */
    ArrayList<String> getSuggestions(String search) throws IOException;

    /**
     * @return the artist name based on the id
     * @param id the artist id on Spotify
     */
    String getArtistname(String id) throws IOException;

    /**
     * @return the track name based on the id
     * @param id the track id on Spotify
     */
    String getTrackName(String id) throws IOException;
}
