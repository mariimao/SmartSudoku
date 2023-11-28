package view;
import data_access.SpotifyDAO;
import app.*;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUserFactory;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyController;
import interface_adapter.spotify.SpotifyState;
import interface_adapter.spotify.SpotifyViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameController;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "new game view";
    private final NewGameViewModel newGameViewModel;
    private final NewGameController newGameController;


    private final SpotifyController spotifyController;

    private final SpotifyViewModel spotifyViewModel;


    private final PlayGameViewModel playGameViewModel;
    private final PlayGameController playGameController;

    // TODO: the viewModel and controller for the board view use case need to be added
    private final JButton createEasyGame;
    private final JButton createHardGame;

    private final JLabel songName;
    private final JTextField songNameInputField = new JTextField(15);
    private final JButton searchSong;
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);

    
    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController, PlayGameViewModel playGameViewModel, PlayGameController playGameController,
                       SpotifyViewModel spotifyViewModel, SpotifyController spotifyController) {
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        this.playGameViewModel = playGameViewModel;
        this.playGameController = playGameController;
        this.spotifyViewModel = spotifyViewModel;
        this.spotifyController = spotifyController;
      
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("SudokuScramble");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        StartViewModel startViewModel = new StartViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PauseGameViewModel pauseGameViewModel = new PauseGameViewModel();
        ResumeGameViewModel resumeGameViewModel = new ResumeGameViewModel();
        MenuViewModel menuViewModel = new MenuViewModel();
        NewGameViewModel newGameViewModel = new NewGameViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        EasyGameViewModel easyGameViewModel = new EasyGameViewModel();
        EndGameViewModel endGameViewModel = new EndGameViewModel();
        PlayGameViewModel playGameViewModel = new PlayGameViewModel();


        // testing userDAO
        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StartView startView = StartUseCaseFactory.create(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
        views.add(startView, startView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, startViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, menuViewModel, startViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        // TODO: Update this when you add more views
        MenuView menuView = MenuUseCaseFactory.create(viewManagerModel, menuViewModel,resumeGameViewModel, loginViewModel, newGameViewModel, userDataAccessObject, leaderboardViewModel);
        views.add(menuView, menuView.viewName);

        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, userDataAccessObject);
        views.add(pausedGameView, pausedGameView.viewName);

        NewGameView newGameView = NewGameUseCaseFactory.create(viewManagerModel,newGameViewModel, userDataAccessObject, playGameViewModel);
        views.add(newGameView, newGameViewModel.getViewName());

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(viewManagerModel,leaderboardViewModel, userDataAccessObject);
        views.add(leaderboardView, leaderboardViewModel.getViewName());

        BoardView boardView = BoardUseCaseFactory.create(viewManagerModel, easyGameViewModel, pauseGameViewModel, endGameViewModel, leaderboardViewModel, menuViewModel, startViewModel, playGameViewModel,userDataAccessObject );
        views.add(boardView, playGameViewModel.getViewName());  // TODO: link neccessary views and viewmodels

        viewManagerModel.setActiveViewName(newGameView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController,
                       PlayGameViewModel playGameViewModel, PlayGameController playGameController) {
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        this.playGameViewModel = playGameViewModel;
        this.playGameController = playGameController;


        newGameViewModel.addPropertyChangeListener(this);
        playGameViewModel.addPropertyChangeListener(this);
        this.setBackground(white);

        // creating the title
        JLabel title = new JLabel("Choose Game Difficulty");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 90));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
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
                            NewGameState newGameState = newGameViewModel.getState();
                            User user = newGameState.getUser();
                            int difficulty = newGameState.getDifficulty();
                            playGameViewModel.getState().setCurrentGame(new GameState(difficulty));
                            GameState newGame = new GameState(difficulty);
                            playGameController.execute(user, playGameViewModel.getState().getCurrentGame(), difficulty );
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
                            User user = newGameViewModel.getState().getUser();
                            playGameController.execute(user, playGameViewModel.getState().getCurrentGame(), difficulty );
                        }
                    }
                }
        );

        songOptions.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        if (e.getSource() instanceof JComboBox) {
                            JComboBox cb = (JComboBox) e.getSource();
                            String chosenSong = (String) cb.getSelectedItem();

                            SpotifyState spotifyState = new SpotifyState(spotifyViewModel.getSpotifyState());
                            spotifyState.setChosenSong(chosenSong);
                            spotifyViewModel.setSpotifyState(spotifyState);

                            System.out.println(chosenSong); //just for testing
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
                            spotifyController.execute(spotifyState.getSearch());

                            songOptions.removeAllItems(); // starts clean
                            ArrayList<String> suggestions = spotifyViewModel.getSpotifyState().getSearchResults();
                            if (!suggestions.isEmpty()) {
                                for (String name : suggestions) {
                                    songOptions.addItem(name);
                                }
                            } else {
                                songOptions.addItem("NONE");
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
