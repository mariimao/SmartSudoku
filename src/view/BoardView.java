package view;

import app.*;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.board.GameState;
import entity.user.CommonUserFactory;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.make_move.MakeMoveController;
import interface_adapter.make_move.MakeMovePresenter;
import interface_adapter.make_move.MakeMoveViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGamePresenter;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameState;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyViewModel;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameInteractor;
import use_case.make_move.MakeMoveInputData;
import use_case.make_move.MakeMoveInteractor;
import use_case.pause_game.PauseGameInteractor;
import use_case.user_move.UserMoveInteractor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static int size = 4;
    private final PlayGameState currentState;
    private final JLabel lives;
    private JPanel board = new JPanel();
    private JTextField[][] box;
    private final JButton endGame;
    private final JButton pauseGame;
    private final JButton makeMove;
    private final JButton startPlaying;
    private final JButton rules;
    private final JPanel buttons;
    private final JLabel timerLabel;
    private final JTextField rowInputField = new JTextField(1);
    private final JTextField columnInputField = new JTextField(1);
    private final JTextField valueInputField = new JTextField(1);
    private final MakeMoveController makeMoveController;


//    public static void main(String[] args) {
//        // Build the main program window, the main panel containing the
//        // various cards, and the layout, and stitch them together.
//
//        // The main application window.
//        JFrame application = new JFrame("SudokuScramble");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//
//        // The various View objects. Only one view is visible at a time.
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        // This keeps track of and manages which view is currently showing.
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        // The data for the views, such as username and password, are in the ViewModels.
//        // This information will be changed by a presenter object that is reporting the
//        // results from the use case. The ViewModels are observable, and will
//        // be observed by the Views.
//        StartViewModel startViewModel = new StartViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//        PauseGameViewModel pauseGameViewModel = new PauseGameViewModel();
//        ResumeGameViewModel resumeGameViewModel = new ResumeGameViewModel();
//        MenuViewModel menuViewModel = new MenuViewModel();
//        NewGameViewModel newGameViewModel = new NewGameViewModel();
//        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
//        EasyGameViewModel easyGameViewModel = new EasyGameViewModel();
//        EndGameViewModel endGameViewModel = new EndGameViewModel();
//        PlayGameViewModel playGameViewModel1 = new PlayGameViewModel();
//        SpotifyViewModel spotifyViewModel = new SpotifyViewModel();
//        MakeMoveViewModel makeMoveViewModel1 = new MakeMoveViewModel();
//
//
//        // testing userDAO
//        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);
//
//        UserDAO userDataAccessObject;
//        try {
//            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
//                    "smartsudoku", "user", new CommonUserFactory());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        StartView startView = StartUseCaseFactory.create(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
//        views.add(startView, startView.viewName);
//
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, startViewModel, userDataAccessObject);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, menuViewModel, playGameViewModel1, pauseGameViewModel, resumeGameViewModel, startViewModel, userDataAccessObject);
//        views.add(loginView, loginView.viewName);
//
//        // TODO: Update this when you add more views
//        MenuView menuView = MenuUseCaseFactory.create(viewManagerModel, menuViewModel, resumeGameViewModel, loginViewModel, newGameViewModel, userDataAccessObject, leaderboardViewModel, playGameViewModel1);
//        views.add(menuView, menuView.viewName);
//
//        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, playGameViewModel1, userDataAccessObject);
//        views.add(pausedGameView, pausedGameView.viewName);
//
//        NewGameView newGameView = NewGameUseCaseFactory.create(viewManagerModel, newGameViewModel, userDataAccessObject, playGameViewModel1, loginViewModel, spotifyViewModel, new SpotifyDAO() );
//        views.add(newGameView, newGameViewModel.getViewName());
//
//        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(viewManagerModel, leaderboardViewModel, userDataAccessObject);
//        views.add(leaderboardView, leaderboardViewModel.getViewName());
//
//        EndGameController endGameController1 = new EndGameController(new EndGameInteractor(userDataAccessObject, new EndGamePresenter(leaderboardViewModel, menuViewModel, endGameViewModel, viewManagerModel)));
//        PauseGameController pauseGameController1 = new PauseGameController(new PauseGameInteractor(userDataAccessObject, new PauseGamePresenter(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel)));
//        MakeMoveController makeMoveController1 = new MakeMoveController(new MakeMoveInteractor(userDataAccessObject, new MakeMovePresenter(makeMoveViewModel1, viewManagerModel)));
//
//        EasyGameController easyGameController1 = new EasyGameController(new UserMoveInteractor(userDataAccessObject, new));
//
//        BoardView boardView = new BoardView(easyGameViewModel, pauseGameController1, pauseGameViewModel, endGameController1, endGameViewModel, playGameViewModel1, makeMoveViewModel1, makeMoveController1);
//        views.add(boardView, boardView.viewName);  // TODO: link neccessary views and viewmodels
//
//        viewManagerModel.setActiveViewName(startView.viewName);  //TODO: change back to startView.viewName
//        viewManagerModel.firePropertyChanged();
//
//        application.pack();
//        application.setVisible(true);
//    }

    public BoardView(EasyGameViewModel easyGameViewModel, EasyGameController easyGameController, PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                                 EndGameController endGameController, EndGameViewModel endGameViewModel,
                                 PlayGameViewModel playGameViewModel, MakeMoveController makeMoveController) {
        this.easyGameViewModel = easyGameViewModel;
        this.easyGameController = easyGameController;
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel = pauseGameViewModel;
        this.endGameController = endGameController;
        this.endGameViewModel = endGameViewModel;
        this.playGameViewModel = playGameViewModel;
        this.makeMoveController = makeMoveController;

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

        timerLabel = new JLabel();
        String start = LocalDateTime.now().toString(); // initialize outside of action listener
        currentState.setStartTime(LocalDateTime.parse(start));

        //TODO: it starts counting from newGameView, not sure how to fix
        // ALSO, cannot pause count when pressing pause
        Timer timer = new Timer(1000, new ActionListener() { //delay is 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                currentState.setTimePlayed(currentState.getTimePlayed() + 1); // increases time played every 1 sec
                int hours = currentState.getTimePlayed() / 60 / 60;
                int minutes = currentState.getTimePlayed() / 60;
                int seconds = currentState.getTimePlayed() % 60;
                timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });
        timer.setRepeats(true);
        timer.start();

//        // Add Timer to GUI
        //timer.setText();
        timerLabel.setFont(new Font("Consolas", Font.ITALIC, 20));
        timerLabel.setBackground(darkblue);
        timerLabel.setForeground(black);
        timerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timerLabel);

        // Add Buttons to GUI
        buttons = new JPanel();
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

        rules = new CustomButton("Game Rules", darkblue, white);
        rules.setFont(new Font("Verdana", Font.BOLD, 16));
        rules.setBackground(white);
        rules.setForeground(darkblue);
        rules.setAlignmentX(JLabel.TOP_ALIGNMENT);
        buttons.add(rules);

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
        timerLabel.setVisible(false);
        lives.setVisible(false);

        rules.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(rules)) {
                            JOptionPane.showMessageDialog(board,
                                    "Welcome to SmartSudoku. We're playing sudoku with a twist.\n" +
                                            "Here are the rules: \n" +
                                            "We have a n x n board where squares need to be filled in with numbers from 1 to n with no repeated numbers in each line, horizontally and vertically.\n" +
                                            "There are also sqrt(n) boxes⏹️ that need to be filled with numbers from 1 to n without repeating.\n" +
                                            "Each time you input a number from 1 to n:\n" +
                                            "- If it is correct, the board will shift 🔀 to a new board with new numbers. The numbers you inputted will stay the same.\n" +
                                            "- If it is wrong, then you lose a life. You have 5 lives in total ♥️♥️♥️♥️♥️\n" +
                                            "You can always pause at any time.\n" +
                                            "There is a timer that keeps track of how long you've spent on the game.\n" +
                                            "HAVE FUN AND ENJOY THE MUSIC!");


                        }
                    }
                }
        );

        // Creating Action Listeners
        startPlaying.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Trigger the property change event when the button is clicked
                firePropertyChange("startPlaying", false, true);
                if (e.getSource().equals(startPlaying)) {
                    // Trigger the property change event when the button is clicked
                    firePropertyChange("startPlaying", false, true);
                    boardReset(buttons, timerLabel);
                }
            }
        });

        endGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(endGame)) {
                            currentState.setTime(currentState.getTimePlayed());
                            endGameController.execute(
                                    currentState.getUserName(),
                                    currentState.getCurrentGame(),
                                    currentState.getTime(),
                                    currentState.getLives(),
                                    currentState.getScores());
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
                            String enteredNumber = "";
                            int x = -1;
                            int y = -1;
                            int inputCount = 0;

                            // When this is clicked, we parse through board to gather the info from what was inputted
                            for (int row = 0; row < size; row++) {
                                for (int col = 0; col < size; col++) {
                                    if (box[row][col].isEditable() && !box[row][col].getText().isEmpty()) {
                                        // Lock the entered number and disable all other JTextFields
                                        enteredNumber = box[row][col].getText();
                                        x = col;
                                        y = row;
                                        box[row][col].setEditable(false);
                                        inputCount ++;

                                    }
                                    // Disable Editting Temporarily
                                    box[row][col].setEditable(false);
                                }
                            }

                            // Checks to see if the user inputted exactly one value
                            if (!(inputCount == 1)) {
                                JOptionPane.showMessageDialog(board, "Please Enter Exactly One Value Per Round");
                                boardReset(buttons, timerLabel);
                            }
                            else {

                                try {
                                    // Checks to see if the user inputted an integer
                                    int enteredNum = Integer.parseInt(enteredNumber);

                                    // Checks to see if the integer entered is a valid number for the board size
                                    if (enteredNum > size || enteredNum < 1) {
                                        JOptionPane.showMessageDialog(board, "Input must be less than or equal to ".concat(String.valueOf(size)).concat(" and greater than 0"));
                                        // Re-enable editing
                                        // Disable all other JTextFields
                                        for (int i = 0; i < size; i++) {
                                            for (int j = 0; j < size; j++) {
                                                if (!box[i][j].getText().isEmpty()) {
                                                    int cellValue = Integer.parseInt(box[i][j].getText());
                                                    if (cellValue > size || cellValue < 1) {  // the box that was inputted a number that was too gets reset to being empty
                                                        box[i][j].setText("");
                                                    }
                                                }
                                                if (box[i][j].getText().isEmpty()) {
                                                    box[i][j].setEditable(true);
                                                }
                                            }
                                        }

                                    }

                                    // if everything is fine, update the view
                                    else {
                                        // recreate the board based on the new scrambled board
                                        // ASSUMPTION: playgameviewmodel.getState.currentGame has been set to the new scrambled board

                                        System.out.println("BoardView");
                                        System.out.println(currentState.getCurrentGame().getCurrBoard());

                                        System.out.println("BoardView solutions");
                                        System.out.println(Arrays.deepToString(currentState.getCurrentGame().getCurrBoard().getSolutionBoard()));

                                        currentState.setCurrentGame(makeMoveController.execute(Integer.parseInt(enteredNumber), x, y, currentState.getCurrentGame()));
                                        lives.setText("LIVES: ".concat(String.valueOf(currentState.getCurrentGame().getLives())));

                                        boardReset(buttons, timerLabel);



                                        if (playGameViewModel.getState().getCurrentGame().getCurrBoard().noSpacesLeft()) {
                                            JOptionPane.showMessageDialog(board, " Congratulations!!! You Solved The Puzzle!!!");
                                            endGameController.execute(
                                                    currentState.getUserName(),
                                                    currentState.getCurrentGame(),
                                                    currentState.getTime(),
                                                    currentState.getLives(),
                                                    currentState.getScores()
                                            );
                                        }
                                    }


                                } catch (NumberFormatException ignored) {
                                    JOptionPane.showMessageDialog(board, "Input Must Be an Integer");

                                    // Re-enable editing
                                    for (int i = 0; i < size; i++) {
                                        for (int j = 0; j < size; j++) {
                                            if (!(box[i][j].getText().matches("\\d+"))) {  // the box that was inputted a non-integer gets reset to being empty
                                                box[i][j].setText("");
                                            }
                                            if (box[i][j].getText().isEmpty()) {
                                                box[i][j].setEditable(true);
                                            }
                                        }
                                    }
                                }
                            }

                            BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void boardReset(JPanel buttons, JLabel timer) {
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
        buttons.setVisible(true);
        timer.setVisible(true);
        lives.setVisible(true);
        BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("PLAYGAMESTATE")) {
            boardReset(buttons, timerLabel);
        }
    }
}
