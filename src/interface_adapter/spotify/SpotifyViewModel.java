package interface_adapter.spotify;

import interface_adapter.ViewModel;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Spotify use case. Extends ViewModel Class.
 */
public class SpotifyViewModel extends ViewModel {
    private static String TITLE_LABEL = "Spotify Search Results";

    private static String CHOOSE_LABEL = "Choose one of the results";

    private SpotifyState spotifyState = new SpotifyState();

    /**
     * Constructor for SpotifyViewModel, inherits from ViewModel
     */
    public SpotifyViewModel() {super("spotify search view");}

    /**
     * Sets Spotify state for viewmodel to use
     * @param spotifyState the state of spotify use case
     */
    public void setSpotifyState(SpotifyState spotifyState) {
        this.spotifyState = spotifyState;
    }

    public final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires property change to view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.spotifyState);
    }

    /**
     * Adds propertychangelistener for support
     * @param listener the listener that listens for property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     *
     * @return the state of spotifyState for view model to update the view
     */
    public SpotifyState getSpotifyState() {
        return this.spotifyState;
    }
}
