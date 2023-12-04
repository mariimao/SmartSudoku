package use_case.spotify;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class representing the interactor for the Spotify usecase. This class implements the SpotifyInputBoundary.
 */
public class SpotifyInteractor implements SpotifyInputBoundary {
    final SpotifyDataAccessInterface spotifyDataAccessInterface;

    final SpotifyOutputBoundary spotifyPresenter;

    /**
     * Constructor for the SpotifyInteractor object.
     * @param spotifyDataAccessInterface is a SpotifyUserDataAccessInterface object
     * @param spotifyPresenter is a SpotifyOutputBoundary object
     */
    public SpotifyInteractor(SpotifyDataAccessInterface spotifyDataAccessInterface,
                             SpotifyOutputBoundary spotifyPresenter) {
        this.spotifyPresenter = spotifyPresenter;
        this.spotifyDataAccessInterface = spotifyDataAccessInterface;
    }

    /**
     * Executes the Spotify UseCase.
     * This function will execute and return search results for the user to choose and select from.
     * If the function does not go through properly, the function will prepare the fail view.
     * @param spotifyInputData is an SpotifyInputData object
     */
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
