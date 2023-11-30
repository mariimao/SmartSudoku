package view;

import app.*;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameState;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyViewModel;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    public final String viewName = "PLAY GAME";

    private final EasyGameViewModel easyGameViewModel;
    private final EasyGameController easyGameController;

    private final PauseGameController pauseGameController;
    private final PauseGameViewModel pauseGameViewModel;

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;
    private final PlayGameViewModel playGameViewModel;

    private static int size = 4; // TODO: change based on newgame input
    private final PlayGameState currentState;
    private final JLabel lives;
    private JPanel board = new JPanel();
    private JTextField[][] box;
    private final JButton endGame;
    private final JButton pauseGame;
    private final JButton makeMove;
    private final JButton startPlaying;
    private final JTextField rowInputField = new JTextField(1);
    private final JTextField columnInputField = new JTextField(1);
    private final JTextField valueInputField = new JTextField(1);


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
        PlayGameViewModel playGameViewModel1 = new PlayGameViewModel();
        SpotifyViewModel spotifyViewModel = new SpotifyViewModel();


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

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, menuViewModel, playGameViewModel1, pauseGameViewModel, resumeGameViewModel, startViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        // TODO: Update this when you add more views
        MenuView menuView = MenuUseCaseFactory.create(viewManagerModel, menuViewModel, resumeGameViewModel, loginViewModel, newGameViewModel, userDataAccessObject, leaderboardViewModel, playGameViewModel1);
        views.add(menuView, menuView.viewName);

        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, playGameViewModel1, userDataAccessObject);
        views.add(pausedGameView, pausedGameView.viewName);

        NewGameView newGameView = NewGameUseCaseFactory.create(viewManagerModel, newGameViewModel, userDataAccessObject, playGameViewModel1, loginViewModel, spotifyViewModel, new SpotifyDAO() );
        views.add(newGameView, newGameViewModel.getViewName());

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(viewManagerModel, leaderboardViewModel, userDataAccessObject);
        views.add(leaderboardView, leaderboardViewModel.getViewName());

        BoardView boardView = BoardUseCaseFactory.create(viewManagerModel, easyGameViewModel, pauseGameViewModel, endGameViewModel, leaderboardViewModel, menuViewModel, startViewModel, playGameViewModel1, userDataAccessObject);
        views.add(boardView, "Board View");  // TODO: link neccessary views and viewmodels

        viewManagerModel.setActiveViewName(boardView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public BoardView(EasyGameViewModel easyGameViewModel, EasyGameController easyGameController,
                     PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                     EndGameController endGameController, EndGameViewModel endGameViewModel,
                     PlayGameViewModel playGameViewModel) {
        this.easyGameViewModel = easyGameViewModel;
        this.easyGameController = easyGameController;
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel = pauseGameViewModel;
        this.endGameController = endGameController;
        this.endGameViewModel = endGameViewModel;
        this.playGameViewModel = playGameViewModel;

        playGameViewModel.addPropertyChangeListener(this);
        easyGameViewModel.addPropertyChangeListener(this);
        this.currentState = playGameViewModel.getState();

        // Creating the Title of the View
        JLabel title = new JLabel("Play Smart Sudoku");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        // Setting the Size of The Board Based on The State's Difficulty
        GameState newGameState = new GameState(currentState.getDifficulty());
        if (currentState.getDifficulty() == 1) {size = 4;}
        else {size = 9;}

        // Setting Up The Layout of The Board
        box = new JTextField[size][size];

        board.setLayout(new GridLayout(size, size));
        board.setAlignmentX(Component.CENTER_ALIGNMENT);
        // board.setSize(500, 500);
        this.add(board);


        // Add Lives to The GUI
        lives = new JLabel();
        lives.setText("LIVES: ".concat(String.valueOf(newGameState.getLives())));
        lives.setFont(new Font("Consolas", Font.ITALIC, 20));
        lives.setBackground(white);
        lives.setForeground(black);
        lives.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(lives);


        // Add Timer to GUI
        JLabel timer = new JLabel(); // TODO: implement timer
        timer.setText("PLACEHOLDER TIMER");
        timer.setFont(new Font("Consolas", Font.ITALIC, 20));
        timer.setBackground(darkblue);
        timer.setForeground(black);
        timer.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timer);

        // Add Buttons to GUI
        JPanel buttons = new JPanel();
        endGame = new CustomButton("End Game", darkblue, white);
        endGame.setFont(new Font("Verdana", Font.BOLD, 16));
        endGame.setBackground(white);
        endGame.setForeground(darkblue);
        endGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(endGame);


        pauseGame = new CustomButton("Pause Game", darkblue, white);
        pauseGame.setFont(new Font("Verdana", Font.BOLD, 16));
        pauseGame.setBackground(white);
        pauseGame.setForeground(darkblue);
        pauseGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(pauseGame);


        makeMove = new CustomButton("Make Move", darkblue, white);
        makeMove.setFont(new Font("Verdana", Font.BOLD, 16));
        makeMove.setBackground(white);
        makeMove.setForeground(darkblue);
        makeMove.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(makeMove);

        startPlaying = new CustomButton("Start Playing Puzzle", darkblue, white);
        startPlaying.setFont(new Font("Verdana", Font.BOLD, 16));
        startPlaying.setBackground(white);
        startPlaying.setForeground(darkblue);
        startPlaying.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JPanel startPlayingPanel = new JPanel();
        startPlayingPanel.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        startPlayingPanel.add(startPlaying);

        this.add(startPlayingPanel);

        LabelTextPanel rowInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.ROW_LABEL), rowInputField);
        rowInfo.setFont(new Font("Verdana", Font.BOLD, 16));
        rowInfo.setBackground(white);
        rowInfo.setForeground(darkblue);
        this.add(rowInfo);

        LabelTextPanel columnInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.COLUMN_LABEL), columnInputField);
        columnInfo.setFont(new Font("Verdana", Font.BOLD, 16));
        columnInfo.setBackground(white);
        columnInfo.setForeground(darkblue);
        this.add(columnInfo);

        LabelTextPanel valueInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.VALUE_LABEL), valueInputField);
        valueInfo.setFont(new Font("Verdana", Font.BOLD, 16));
        valueInfo.setBackground(white);
        valueInfo.setForeground(darkblue);
        this.add(valueInfo);


        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);
        rowInfo.setVisible(false);
        columnInfo.setVisible(false);
        valueInfo.setVisible(false);
        buttons.setVisible(false);
        timer.setVisible(false);
        lives.setVisible(false);


        // Creating Action Listeners
        startPlaying.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Trigger the property change event when the button is clicked
                firePropertyChange("startPlaying", false, true);
                if (e.getSource().equals(startPlaying)) {
                    // Trigger the property change event when the button is clicked
                    firePropertyChange("startPlaying", false, true);

                    // Set the layout manager to BoxLayout with Y_AXIS
                    BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
                    GameState newGameState = currentState.getCurrentGame();
                    if (currentState.getDifficulty() == 1) {size = 4;}
                    else {size = 9;}
                    board.removeAll();
                    box = new JTextField[size][size];
                    board.setLayout(new GridLayout(size, size));

                    ArrayList<Integer> values = newGameState.getCurrBoard().toArray();
                    int i = 0;
                    int cellsize = 5;
                    JTextField lastFocusedTextField;
                    lastFocusedTextField = null;

                    for (int row = 0; row < size; row++) {
                        for (int col = 0; col < size; col++) {
                            JTextField number = new JTextField();
                            number.setSize(new Dimension(cellsize, cellsize));
                            number.setHorizontalAlignment(JTextField.CENTER);
                            number.setFont(new Font("Arial", Font.PLAIN, 20));

                            if (values.get(i) != 0) {
                                number.setText(String.valueOf(values.get(i)));
                                number.setEditable(false);
                            }

                            box[row][col] = number;
                            board.add(number);
                            i++;
                        }
                    }
                    board.revalidate();
                    board.repaint();
                    startPlaying.setVisible(false);
                    rowInfo.setVisible(true);
                    columnInfo.setVisible(true);
                    valueInfo.setVisible(true);
                    buttons.setVisible(true);
                    timer.setVisible(true);
                    lives.setVisible(true);
                    BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));

                }
            }
        });

        endGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(endGame)) {
                            EndGameState endGameState = endGameViewModel.getState();
                            endGameController.execute(
                                    endGameState.getUser(),
                                    endGameState.getEndGame(),
                                    endGameState.getTime(),
                                    endGameState.getLives()
                            );
                        }
                    }
                }
        );

        pauseGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(pauseGame)) {
                            pauseGameController.execute(
                                    currentState.getUserName(),
                                    currentState.getCurrentGame(),
                                    currentState.getCurrentGame().getPastStates()

                            );

                        }
                    }
                }
        );

        makeMove.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(makeMove)) {
                            easyGameViewModel.getState().setEasyGame(newGameState);
                            EasyGameState currentState = BoardView.this.easyGameViewModel.getState();
                            BoardView.this.easyGameController.execute(
                                    currentState.getEasyGame(),
                                    currentState.getRow(),
                                    currentState.getColumn(),
                                    currentState.getValue()
                            );

                            BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
                            GameState newGameState = currentState.getEasyGame();
                            if (currentState.getDifficulty() == 1) {size = 4;}
                            else {size = 9;}
                            board.removeAll();
                            box = new JTextField[size][size];
                            board.setLayout(new GridLayout(size, size));

                            ArrayList<Integer> values = newGameState.getCurrBoard().toArray();
                            int i = 0;

                            for (int row = 0; row < size; row++) {
                                for (int col = 0; col < size; col++) {
                                    JTextField number = new JTextField();
                                    number.setPreferredSize(new Dimension(20, 20));
                                    number.setHorizontalAlignment(JTextField.CENTER);
                                    number.setFont(new Font("Arial", Font.PLAIN, 20));

                                    if (values.get(i) != 0) {
                                        number.setText(String.valueOf(values.get(i)));
                                    }

                                    box[row][col] = number;
                                    board.add(number);
                                    i++;
                                }
                            }
                            lives.setText("LIVES: ".concat(String.valueOf(newGameState.getLives())));
                            board.revalidate();
                            board.repaint();
                            startPlaying.setVisible(false);
                            rowInfo.setVisible(true);
                            columnInfo.setVisible(true);
                            valueInfo.setVisible(true);
                            buttons.setVisible(true);
                            timer.setVisible(true);
                            lives.setVisible(true);
                            BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
                        }
                    }
                }
        );

        rowInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EasyGameState currentState = easyGameViewModel.getState();
                        int row = Integer.parseInt(rowInputField.getText() + e.getKeyChar());
                        currentState.setRow(row-1);
                        easyGameViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        columnInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EasyGameState currentState = easyGameViewModel.getState();
                        int column = Integer.parseInt(columnInputField.getText() + e.getKeyChar());
                        currentState.setColumn(column-1);
                        easyGameViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        valueInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EasyGameState currentState = easyGameViewModel.getState();
                        int value = Integer.parseInt(valueInputField.getText() + e.getKeyChar());
                        currentState.setValue(value);
                        easyGameViewModel.setState(currentState);
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
