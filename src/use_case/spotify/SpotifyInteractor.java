package use_case.spotify;

import java.io.IOException;
import java.util.ArrayList;

public class SpotifyInteractor implements SpotifyInputBoundary {
    final SpotifyDataAccessInterface spotifyDataAccessInterface;

    final SpotifyOutputBoundary spotifyPresenter;

    public SpotifyInteractor(SpotifyDataAccessInterface spotifyDataAccessInterface,
                             SpotifyOutputBoundary spotifyPresenter) {
        this.spotifyPresenter = spotifyPresenter;
        this.spotifyDataAccessInterface = spotifyDataAccessInterface;
    }
    @Override
    public void execute(SpotifyInputData spotifyInputData) throws IOException {
        String searchKey = spotifyInputData.getSearchKey();
        ArrayList<String> searchResults = spotifyDataAccessInterface.getSuggestions(searchKey);
        ArrayList<String> searchWithNames = new ArrayList<>();
        for (String id : searchResults) {
            searchWithNames.add(spotifyDataAccessInterface.getTrackName(id)); // may want to add artists name but thats not working rn
        }
        if (!searchResults.isEmpty()) {
            SpotifyOutputData spotifyOutputData = new SpotifyOutputData(searchWithNames, false);
            spotifyPresenter.prepareSuccessView(spotifyOutputData);
        } else {
            spotifyPresenter.prepareFailView("Could not find a song.");
        }
    }
}
