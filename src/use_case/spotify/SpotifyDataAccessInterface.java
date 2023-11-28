package use_case.spotify;

import java.util.ArrayList;

public interface SpotifyDataAccessInterface {
    public ArrayList<String> getSuggestions(String search);

    public String getArtistname(String id);

    public String getTrackName(String id);
}
