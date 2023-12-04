package use_case.spotify;

/**
 * Class representing the input data of the Spotify
 */
public class SpotifyInputData {
    private final String searchKey;

    /**
     * Constructor for the SpotifyInputData object.
     * @param searchKey the username of the user
     */
    public SpotifyInputData(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * @return the search key for spotify
     */
    public String getSearchKey() {
        return searchKey;
    }
}
