package interface_adapter.play_music;

import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInputData;

import java.io.IOException;

/**
 * The class for PlayMusicController. Acts as controller to send information to PlayMusicInteractor.
 */
public class PlayMusicController {

    final PlayMusicInputBoundary playMusicInteractor;

    /**
     * Constructor of PlayMusicController
     * @param playMusicInteractor the interactor that makes decisions with input data
     */
    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor) {
        this.playMusicInteractor = playMusicInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     * @param chosenSong            the name of the chosen song as a string
     * @param chosenSongPlace       the spot they selected
     * @param search                the searchkey they used
     * @throws IOException          throws exception if they can't find the song
     */
    public void execute(String chosenSong, int chosenSongPlace, String search) throws IOException {
        PlayMusicInputData playMusicInputData = new PlayMusicInputData(chosenSong, chosenSongPlace, search);
        playMusicInteractor.execute(playMusicInputData);
    }

}
