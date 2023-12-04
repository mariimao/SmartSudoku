package use_case.spotify;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class representing the output data for Spotify.
 */
public class SpotifyOutputData {
    public final ArrayList<String> searchResults;

    public boolean useCaseFailed;

    /**
     * Constructor for an SpotifyOutputData object.
     * @param searchResults is an array of the search results returned from the API
     * @param useCaseFailed is a boolean that determines if the use case was successful
     */
    public SpotifyOutputData(ArrayList<String> searchResults, boolean useCaseFailed) {
        this.searchResults = searchResults;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * @return the search results
     */
    public ArrayList<String> getSearchResults() {
        return searchResults;
    }

    /**
     * @return if the use case failed
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
