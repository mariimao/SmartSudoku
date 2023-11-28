package use_case.spotify;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpotifyOutputData {
    public final ArrayList<String> searchResults;

    public boolean useCaseFailed;

    public SpotifyOutputData(ArrayList<String> searchResults, boolean useCaseFailed) {
        this.searchResults = searchResults;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getSearchResults() {
        return searchResults;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
