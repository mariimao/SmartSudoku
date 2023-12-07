package interface_adapter.spotify;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Spotify use case. Extends ViewModel Class.
 */
public class SpotifyViewModel extends ViewModel {
    private static final String TITLE_LABEL = "Spotify Search Results";

    private static final String CHOOSE_LABEL = "Choose one of the results";
    public final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SpotifyState spotifyState = new SpotifyState();


    /**
     * Constructor for SpotifyViewModel, inherits from ViewModel
     */
    public SpotifyViewModel() {
        super("spotify search view");
    }

    /**
     * @return the state of spotifyState for view model to update the view
     */
    public SpotifyState getSpotifyState() {
        return this.spotifyState;
    }

    /**
     * Sets Spotify state for viewmodel to use
     *
     * @param spotifyState the state of spotify use case
     */
    public void setSpotifyState(SpotifyState spotifyState) {
        this.spotifyState = spotifyState;
    }

    /**
     * Fires property change to view model
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.spotifyState);
    }

    /**
     * Adds propertychangelistener for support
     *
     * @param listener the listener that listens for property changes
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
