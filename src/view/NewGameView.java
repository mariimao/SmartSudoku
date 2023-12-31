package view;

import entity.board.GameState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.play_music.PlayMusicController;
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
import java.io.IOException;
import java.util.ArrayList;

/**
 * View for the NewGameView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class NewGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "new game view";
    private final NewGameViewModel newGameViewModel;
    private final NewGameController newGameController;
    private final PlayGameViewModel playGameViewModel;
    private final PlayGameController playGameController;
    private final SpotifyController spotifyController;
    private final SpotifyViewModel spotifyViewModel;
    private final LoginViewModel loginViewModel;

    private final PlayMusicController playMusicController;

    private final JButton createEasyGame;
    private final JButton createHardGame;

    private final JLabel songName;
    private final JTextField songNameInputField = new JTextField(15);
    private final JButton searchSong;
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);


    /**
     * Constructor for NewGame View
     *
     * @param loginViewModel     the view model for login usecase, is a LoginViewModel object
     * @param newGameController  the controller for new game, is NewGameController object
     * @param newGameViewModel   the view model for new game, is NewGameViewModel object
     * @param playGameViewModel  the view model for playgame, is PlayGameViewModel object
     * @param playGameController the controller for playgame, is PlayGameController object
     * @param spotifyController  the controller for spotify use case, is SpotifyController object
     * @param spotifyViewModel   the view model for spotify use case, is SpotifyViewModel object
     */
    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController, PlayGameViewModel playGameViewModel, PlayGameController playGameController,
                       SpotifyViewModel spotifyViewModel, SpotifyController spotifyController, LoginViewModel loginViewModel, PlayMusicController playMusicController) {
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        this.playGameViewModel = playGameViewModel;
        this.playGameController = playGameController;
        this.spotifyViewModel = spotifyViewModel;
        this.spotifyController = spotifyController;
        this.loginViewModel = loginViewModel;
        this.playMusicController = playMusicController;

        newGameViewModel.addPropertyChangeListener(this);
        playGameViewModel.addPropertyChangeListener(this);
        this.setBackground(white);

        // creating the title
        JLabel title = new JLabel("Choose Game Difficulty");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 90));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10, 40, 10, 40)));
        this.add(title);

        //  creating the buttons
        JPanel buttons = new JPanel();
        createEasyGame = new CustomButton("Easy Game", darkblue, white);
        createEasyGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(createEasyGame);
        createHardGame = new CustomButton("Hard Game", darkblue, white);
        createHardGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(createHardGame);
        this.add(buttons);

        //song selection
        JPanel songSelection = new JPanel();
        songName = new JLabel(NewGameViewModel.SPOTIFY_LABEL);
        songName.setFont(new Font("Consolas", Font.ITALIC, 20));
        songName.setForeground(white);
        songNameInputField.setBorder(new BevelBorder(10, blue, black));
        songNameInputField.setBackground(white);
        songNameInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        songNameInputField.setForeground(darkblue);
        LabelTextPanel songNameInfo = new LabelTextPanel(songName, songNameInputField);
        songNameInfo.setBackground(darkblue);
        songSelection.add(songNameInfo);
        searchSong = new CustomButton("Search", white, darkblue);
        songSelection.add(searchSong);
        this.add(songSelection);

        JComboBox<String> songOptions = new JComboBox<>();
        this.add(songOptions);

        createEasyGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createEasyGame)) {
                            int difficulty = 1;
                            newGameViewModel.getState().setDifficulty(difficulty);
                            playGameViewModel.getState().setCurrentGame(new GameState(difficulty));
                            playGameViewModel.getState().setUserName(newGameViewModel.getState().getUserName());
                            playGameController.execute(newGameViewModel.getState().getUserName(), playGameViewModel.getState().getCurrentGame(), difficulty);
                        }
                    }
                }
        );

        createHardGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createHardGame)) {
                            int difficulty = 2;
                            newGameViewModel.getState().setDifficulty(difficulty);
                            playGameViewModel.getState().setDifficulty(difficulty);
                            playGameViewModel.getState().setCurrentGame(new GameState(difficulty));
                            playGameViewModel.getState().setUserName(loginViewModel.getLoginState().getUsername());
                            playGameController.execute(loginViewModel.getLoginState().getUsername(), playGameViewModel.getState().getCurrentGame(), difficulty);
                        }
                    }
                }
        );

        songOptions.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            if (e.getSource() instanceof JComboBox) {
                                JComboBox cb = (JComboBox) e.getSource();
                                if (cb.getSelectedIndex() != 0) {
                                    String chosenSong = (String) cb.getSelectedItem();

                                    SpotifyState spotifyState = new SpotifyState(spotifyViewModel.getSpotifyState());
                                    spotifyState.setChosenSong(chosenSong);
                                    spotifyViewModel.setSpotifyState(spotifyState);

                                    int chosenSongPlace = cb.getSelectedIndex();
                                    try {
                                        playMusicController.execute(chosenSong, chosenSongPlace, spotifyViewModel.getSpotifyState().getSearch());
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }
                        }
                    }
                }
        );

        searchSong.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchSong)) {
                            SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
                            try {
                                spotifyController.execute(spotifyState.getSearch());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            songOptions.removeAllItems(); // starts clean
                            songOptions.addItem("Check search results.");
                            ArrayList<String> suggestions = spotifyViewModel.getSpotifyState().getSearchResults();
                            for (String name : suggestions) {
                                songOptions.addItem(name);
                            }

                        }
                    }
                }
        );

        songNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SpotifyState spotifyState = spotifyViewModel.getSpotifyState();
                        String song = songNameInputField.getText() + e.getKeyChar();
                        spotifyState.setSearch(song);
                        spotifyViewModel.setSpotifyState(spotifyState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    /**
     * Records the action performed
     *
     * @param e the action that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Records and notifies of any property change
     *
     * @param evt the propertychange event that is fired by the viewmodel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
