package use_case.play_music;

public class PlayMusicInputData {
    final private String chosenSong;
    final private int chosenSongPlace;

    final private String search;

    public PlayMusicInputData(String chosenSong, int chosenSongPlace, String search) {
        this.chosenSong = chosenSong;
        this.chosenSongPlace = chosenSongPlace;
        this.search = search;
    }

    public int getChosenSongPlace(){
        return chosenSongPlace;
    }

    public String getSearch() {
        return search;
    }
}
