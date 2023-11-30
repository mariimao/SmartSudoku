package interface_adapter.spotify;

import java.util.ArrayList;

public class SpotifyState {

    private String search = "";

    private String chosenSong = "";
    private ArrayList<String> searchResults = new ArrayList<>();

    private String searchResultsError = null;

    public SpotifyState(SpotifyState copy) {
        this.search = copy.search;
        this.chosenSong = copy.chosenSong;
        this.searchResults = copy.searchResults;
        this.searchResultsError = copy.searchResultsError;
    }

    public SpotifyState(){}

    public ArrayList<String> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(ArrayList<String> searchResults) {
        this.searchResults = searchResults;
    }

    public String getSearchResultsError() {
        return searchResultsError;
    }

    public void setSearchResultsError(String searchResultsError) {
        this.searchResultsError = searchResultsError;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getChosenSong() {
        return chosenSong;
    }

    public void setChosenSong(String chosenSong) {
        this.chosenSong = chosenSong;
    }
}
