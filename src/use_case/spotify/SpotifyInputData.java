package use_case.spotify;

public class SpotifyInputData {
    private final String searchKey;

    public SpotifyInputData(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }
}
