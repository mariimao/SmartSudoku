package use_case.spotify;

import java.io.IOException;
import java.util.ArrayList;

public interface SpotifyDataAccessInterface {
    public ArrayList<String> getSuggestions(String search) throws IOException;

    public String getArtistname(String id) throws IOException;

    public String getTrackName(String id) throws IOException;
}
