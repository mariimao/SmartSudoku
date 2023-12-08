package use_case.play_music;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for the data access object of play music use cases. Implemented by UserDAO.
 */
public interface PlayMusicDataAccessInterface {

    /**
     * Gets the position of the track.
     *
     * @param id the id of the track
     */

    Integer getTrackPosition(String id) throws IOException;

    /**
     * Gets the id of the album.
     *
     * @param id the id of the track
     */
    String getAlbumID(String id) throws IOException;

    /**
     * Gets the name of the track.
     *
     * @param id the id of the track
     */
    String getTrackName(String id) throws IOException;

    /**
     * Gets suggestions based on user input
     *
     * @param search the user's requested track
     */

    ArrayList<String> getSuggestions(String search) throws IOException;

    /**
     * Gets the API token.
     */

    String getApiToken();

    /**
     * Gets the Spotify access code.
     */
    String getAccessCode() throws IOException;

    /**
     * Requests Spotify authorization.
     */

    String requestAuthorization() throws IOException;

    /**
     * Gets the Spotify refresh token
     */
    String getRefreshToken() throws IOException;

}
