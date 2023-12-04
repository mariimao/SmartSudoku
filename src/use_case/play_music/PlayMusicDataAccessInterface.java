package use_case.play_music;

import java.io.IOException;
import java.util.ArrayList;

public interface PlayMusicDataAccessInterface {

    Integer getTrackPosition(String id) throws IOException;

    String getAlbumID(String id) throws IOException;

    String getTrackName(String id) throws IOException;

    ArrayList<String> getSuggestions(String search) throws IOException;

    String getApiToken();

    String getAccessCode() throws IOException;

    String requestAuthorization() throws IOException;

    String getRefreshToken() throws IOException;

}
