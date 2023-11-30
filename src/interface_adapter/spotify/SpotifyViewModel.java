package interface_adapter.spotify;

import interface_adapter.ViewModel;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SpotifyViewModel extends ViewModel {
    private static String TITLE_LABEL = "Spotify Search Results";

    private static String CHOOSE_LABEL = "Choose one of the results";

    private SpotifyState spotifyState = new SpotifyState();

    public SpotifyViewModel() {super("spotify search view");}

    public void setSpotifyState(SpotifyState spotifyState) {
        this.spotifyState = spotifyState;
    }

    public final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.spotifyState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SpotifyState getSpotifyState() {
        return this.spotifyState;
    }
}
