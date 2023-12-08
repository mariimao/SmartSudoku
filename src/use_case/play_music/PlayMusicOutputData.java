package use_case.play_music;

/**
 * Class representing the output data for play music.
 */

public class PlayMusicOutputData {
    private final String songName;

    /**
     * Constructor for an SpotifyOutputData object.
     *
     * @param songName is a string representing the song name
     */

    public PlayMusicOutputData(String songName) {
        this.songName = songName;
    }

    /**
     * @return the song name
     */

    public String getSongName() {
        return songName;
    }
}
