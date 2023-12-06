import data_access.SpotifyDAO;
import interface_adapter.play_music.PlayMusicController;
import interface_adapter.spotify.SpotifyState;
import interface_adapter.spotify.SpotifyViewModel;
import org.junit.Test;
import use_case.play_music.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class PlayMusicTest {
    @Test
    public void testInteractorFails() throws IOException {
        PlayMusicDataAccessInterface spotifyDataBase = new SpotifyDAO();
        PlayMusicOutputBoundary creationPresenter = new PlayMusicOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayMusicOutputData playMusicOutputData) {
                fail("Use case failed but is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(true);
            }
        };

        PlayMusicInputBoundary interactor = new PlayMusicInteractor(spotifyDataBase, creationPresenter);
        PlayMusicController playMusicController = new PlayMusicController(interactor);
        SpotifyState spotifyState = new SpotifyState();
        spotifyState.setSearch("drivers license");
        SpotifyViewModel spotifyViewModel = new SpotifyViewModel();
        spotifyViewModel.setSpotifyState(spotifyState);
        playMusicController.execute("drivers license", 1, spotifyViewModel.getSpotifyState().getSearch());
    }

    @Test
    public void testPlayMusicOutputData() {
        PlayMusicOutputData playMusicOutputData = new PlayMusicOutputData("drivers license");
        assertEquals(playMusicOutputData.getSongName(), "drivers license");
    }
}
