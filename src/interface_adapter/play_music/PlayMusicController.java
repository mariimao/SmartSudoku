package interface_adapter.play_music;

import use_case.play_game.PlayGameDataAccessInterface;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInputData;

import java.io.IOException;

public class PlayMusicController {

    final PlayMusicInputBoundary playMusicInteractor;

    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor) {
        this.playMusicInteractor = playMusicInteractor;
    }
    public void execute(String chosenSong, int chosenSongPlace, String search) throws IOException {
        PlayMusicInputData playMusicInputData = new PlayMusicInputData(chosenSong, chosenSongPlace, search);
        playMusicInteractor.execute(playMusicInputData);
    }

}
