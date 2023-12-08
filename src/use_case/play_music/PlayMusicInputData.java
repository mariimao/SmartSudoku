package use_case.play_music;

/**
 * Class representing the input data of the play music use case
 */
public class PlayMusicInputData {
    final private String chosenSong;
    final private int chosenSongPlace;

    final private String search;

    /**
     * Constructor for the PlayMusicInputData object.
     *
     * @param chosenSong the song that the user chose
     * @param chosenSongPlace where the song is on the album
     * @param search what the user searched
     */

    public PlayMusicInputData(String chosenSong, int chosenSongPlace, String search) {
        this.chosenSong = chosenSong;
        this.chosenSongPlace = chosenSongPlace;
        this.search = search;
    }

    /**
     * @return the place the chose song lies on the album
     */

    public int getChosenSongPlace() {
        return chosenSongPlace;
    }

    /**
     * @return what the user searched
     */

    public String getSearch() {
        return search;
    }
}
