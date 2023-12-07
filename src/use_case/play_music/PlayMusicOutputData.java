package use_case.play_music;

public class PlayMusicOutputData {
    private final String songName;

    public PlayMusicOutputData(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }
}
