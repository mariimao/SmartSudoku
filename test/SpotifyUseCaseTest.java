import data_access.SpotifyDAO;
import org.junit.Test;
import use_case.spotify.*;

import static org.junit.Assert.*;

public class SpotifyUseCaseTest {

    SpotifyDataAccessInterface userDataBase;

    @Test
    public void testSuccessSearch() {

        SpotifyInputData inputData = new SpotifyInputData("bad idea");
        SpotifyDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new SpotifyDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        SpotifyOutputBoundary spotifyPresenter = new SpotifyOutputBoundary() {
            @Override
            public void prepareSuccessView(SpotifyOutputData spotifyOutputData) {
                assertEquals(spotifyOutputData.getSearchResults(), spotifyOutputData.searchResults);
                assertFalse(spotifyOutputData.searchResults.isEmpty());
                assertFalse(spotifyOutputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        SpotifyInteractor interactor = new SpotifyInteractor(userDataAccessObject, spotifyPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testSearchFailed() {
        SpotifyInputData inputData = new SpotifyInputData("+-=-=-=-=-=");
        SpotifyDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new SpotifyDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        SpotifyOutputBoundary spotifyPresenter = new SpotifyOutputBoundary() {
            @Override
            public void prepareSuccessView(SpotifyOutputData spotifyOutputData) {
                fail("Use case succeeded but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Could not find a song.", error);
            }
        };

        SpotifyInteractor interactor = new SpotifyInteractor(userDataAccessObject, spotifyPresenter);
        interactor.execute(inputData);

    }
}
