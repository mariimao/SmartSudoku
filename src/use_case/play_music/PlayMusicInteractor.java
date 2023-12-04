package use_case.play_music;

import data_access.SpotifyDAO;
import entity.SpotifyPlayer;

import java.io.IOException;
import java.util.ArrayList;

public class PlayMusicInteractor implements PlayMusicInputBoundary{
    final PlayMusicDataAccessInterface playMusicDataAccessInterface;
    final PlayMusicOutputBoundary playMusicPresenter;

    public PlayMusicInteractor(PlayMusicDataAccessInterface playMusicDataAccessInterface, PlayMusicOutputBoundary playMusicPresenter){
        this.playMusicDataAccessInterface = playMusicDataAccessInterface; //no presenters needed
        this.playMusicPresenter = playMusicPresenter;
    }

    @Override
    public void execute(PlayMusicInputData playMusicInputData) throws IOException {
        int chosenSongPlace = playMusicInputData.getChosenSongPlace() - 1;
        String search = playMusicInputData.getSearch();
        String songID = playMusicDataAccessInterface.getSuggestions(search).get(chosenSongPlace);
        if (!songID.isEmpty()) {
            String token = playMusicDataAccessInterface.getRefreshToken();

            SpotifyPlayer spotifyPlayer = new SpotifyPlayer(token);
            String album_id = playMusicDataAccessInterface.getAlbumID(songID);
            Integer position = playMusicDataAccessInterface.getTrackPosition(songID);
            int positions = position.intValue();
            String name = playMusicDataAccessInterface.getTrackName(songID);
            ArrayList<String> devices = spotifyPlayer.getDevices();
            if (!devices.isEmpty()) {
                String token2 = playMusicDataAccessInterface.getRefreshToken();
                SpotifyPlayer spotifyPlayer1 = new SpotifyPlayer(token2);
                spotifyPlayer1.play(album_id, positions, devices.get(0));

                PlayMusicOutputData playMusicOutputData = new PlayMusicOutputData(name);
                playMusicPresenter.prepareSuccessView(playMusicOutputData);
            } else {
                playMusicPresenter.prepareFailView("Could not find a device.");
            }
        } else {
            playMusicPresenter.prepareFailView("Could not find song.");
        }
    }
}
