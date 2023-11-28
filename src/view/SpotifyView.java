package view;

import data_access.SpotifyDAO;
import entity.board.GameState;
import entity.user.User;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyController;
import interface_adapter.spotify.SpotifyState;
import interface_adapter.spotify.SpotifyViewModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SpotifyView extends JPanel implements ActionListener, PropertyChangeListener, ItemListener {
    public final String viewName = "spotify search view";
    private final SpotifyController spotifyController;
    private final SpotifyViewModel spotifyViewModel;

    private final NewGameViewModel newGameViewModel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);

    private final JComboBox<String> searchResults;

    public SpotifyView(SpotifyViewModel spotifyViewModel, SpotifyController spotifyController, NewGameViewModel newGameViewModel) {

        this.spotifyViewModel = spotifyViewModel;
        this.spotifyController = spotifyController;
        this.newGameViewModel = newGameViewModel;

        spotifyViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        // creating the title
        JLabel title = new JLabel("Spotify search results");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
        ArrayList<String> suggestions = spotifyState.getSearchResults();
        if (!suggestions.isEmpty()) {
            searchResults = new JComboBox<>((ComboBoxModel) suggestions);
        } else {
            searchResults = new JComboBox<>();
            searchResults.addItem("NONE");
            searchResults.addItem("banan");
        }
        this.add(searchResults);

        searchResults.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED) {
                            Object source = e.getSource();
                            if (source instanceof JComboBox) {
                                JComboBox cb = (JComboBox)source;
                                String selectedItem = (String) cb.getSelectedItem();
                                SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
                                spotifyState.setChosenSong(selectedItem);
                                spotifyViewModel.setSpotifyState(spotifyState);
                                System.out.println(selectedItem);
                            }
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
