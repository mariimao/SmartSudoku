package view;

import app.*;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGamePresenter;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameState;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;
import use_case.user_move.UserMoveInteractor;
import use_case.user_move.UserMoveOutputBoundary;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public final String viewName = "Board View";

    private final EasyGameViewModel easyGameViewModel;
    private final EasyGameController easyGameController;

    private final PauseGameController pauseGameController;
    private final PauseGameViewModel pauseGameViewModel;

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;
    private final NewGameViewModel newGameViewModel;

    private static int size = 4; // TODO: change based on newgame input
    private final NewGameState currentState;
    private final JLabel lives;
    private JPanel board = new JPanel();
    private JTextField[][] box;
    private final JButton endGame;
    private final JButton pauseGame;
    private final JButton makeMove;


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
        MenuView menuView = MenuUseCaseFactory.create(viewManagerModel, menuViewModel, resumeGameViewModel, loginViewModel, newGameViewModel, userDataAccessObject, leaderboardViewModel);
        views.add(menuView, menuView.viewName);

        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, userDataAccessObject);
        views.add(pausedGameView, pausedGameView.viewName);

        NewGameView newGameView = NewGameUseCaseFactory.create(viewManagerModel, newGameViewModel, userDataAccessObject);
        views.add(newGameView, newGameViewModel.getViewName());

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(viewManagerModel, leaderboardViewModel, userDataAccessObject);
        views.add(leaderboardView, leaderboardViewModel.getViewName());

        BoardView boardView = BoardUseCaseFactory.create(viewManagerModel, easyGameViewModel, pauseGameViewModel, endGameViewModel, newGameViewModel, leaderboardViewModel, menuViewModel, startViewModel, userDataAccessObject);
        views.add(boardView, "Board View");  // TODO: link neccessary views and viewmodels

        viewManagerModel.setActiveViewName(boardView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public BoardView(EasyGameViewModel easyGameViewModel, EasyGameController easyGameController,
                     PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                     EndGameController endGameController, EndGameViewModel endGameViewModel,
                     NewGameViewModel newGameViewModel) {
        this.easyGameViewModel = easyGameViewModel;
        this.easyGameController = easyGameController;
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel = pauseGameViewModel;
        this.endGameController = endGameController;
        this.endGameViewModel = endGameViewModel;
        this.newGameViewModel = newGameViewModel;

        this.currentState = newGameViewModel.getState();

        // Creating the Title of the View
        JLabel title = new JLabel("Play Smart Sudoku");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setForeground(white);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        // Creating a Random New Game if None is Passed in
        GameState newGameState = currentState.getGame();
        if (newGameState == null) {
            newGameState = new GameState(2);  // TODO: change based currentState.getDifficulty
        }

        // Setting the Size of The Board Based on The State's Difficulty
        if (newGameState.getDifficulty() == 1) {size = 4;}
        else {size = 9;}

        // TODO: add this to viewmodel property change listener
        // Setting Up The Layout of The Board
        box = new JTextField[size][size];

        board.setLayout(new GridLayout(size, size));
        board.setAlignmentX(Component.CENTER_ALIGNMENT);
        board.setSize(500, 500);

        // Add Values to The Board Based on The Game State
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
        endGame = new JButton("End Game");
        endGame.setFont(new Font("Verdana", Font.BOLD, 16));
        endGame.setBackground(white);
        endGame.setForeground(darkblue);
        buttons.add(endGame);


        pauseGame = new JButton("Pause Game");
        pauseGame.setFont(new Font("Verdana", Font.BOLD, 16));
        pauseGame.setBackground(white);
        pauseGame.setForeground(darkblue);
        buttons.add(pauseGame);


        makeMove = new JButton("Make Move");
        makeMove.setFont(new Font("Verdana", Font.BOLD, 16));
        makeMove.setBackground(white);
        makeMove.setForeground(darkblue);
        buttons.add(makeMove);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);


        // Creating Action Listeners
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
                            PauseGameState pauseGameState = pauseGameViewModel.getState();
                            pauseGameController.execute(
                                    pauseGameState.getUser(),
                                    pauseGameState.getPausedGame(),
                                    pauseGameState.getPastGames()

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
                            EasyGameState currentState = BoardView.this.easyGameViewModel.getState();
                            BoardView.this.easyGameController.execute(
                                    currentState.getEasyGame(),
                                    currentState.getRow(),
                                    currentState.getColumn(),
                                    currentState.getValue()
                            );
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
}
