package interface_adapter.spotify;

import java.util.ArrayList;

/**
 * The state of the Spotify ViewModel
 */
public class SpotifyState {

    private String search = "";

    private String chosenSong = "";
    private ArrayList<String> searchResults = new ArrayList<>();

    private String searchResultsError = null;

    /**
     * Copy constructor of Spotify State, makes a copy
     *
     * @param copy the copy of the state that it replicates from
     */
    public SpotifyState(SpotifyState copy) {
        this.search = copy.search;
        this.chosenSong = copy.chosenSong;
        this.searchResults = copy.searchResults;
        this.searchResultsError = copy.searchResultsError;
    }

    /**
     * Default Constructor of SpotifyState
     */
    public SpotifyState() {
    }

    /**
     * @return the search results from the API call
     */
    public ArrayList<String> getSearchResults() {
        return searchResults;
    }

    /**
     * Sets the search results to state to prepare for displaying in the View
     *
     * @param searchResults the search results retrieved from the API call
     */
    public void setSearchResults(ArrayList<String> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the error for searching results if unsuccessful
     */
    public String getSearchResultsError() {
        return searchResultsError;
    }

    /**
     * Sets the error for the searchResultsError to display through the View
     *
     * @param searchResultsError the string error that describes the error that occured
     */
    public void setSearchResultsError(String searchResultsError) {
        this.searchResultsError = searchResultsError;
    }

    /**
     * @return search prompt inputted by the user
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets the search prompt inputted by the user
     *
     * @param search the search prompt
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the chosen song from the user
     */
    public String getChosenSong() {
        return chosenSong;
    }

    /**
     * Sets the chosen song from the user into the view
     *
     * @param chosenSong the chosen song selected by the user
     */
    public void setChosenSong(String chosenSong) {
        this.chosenSong = chosenSong;
    }
}
