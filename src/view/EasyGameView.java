package view;

import app.*;
import data_access.UserDAO;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGamePresenter;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGamePresenter;
import interface_adapter.pause_game.PauseGameState;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;
import use_case.pause_game.PauseGameInteractor;
import use_case.user_move.UserMoveInteractor;
import use_case.user_move.UserMoveOutputBoundary;
import use_case.user_move.UserMoveOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EasyGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "easy game view";

    private final EasyGameViewModel easyGameViewModel;
    private final EasyGameController easyGameController;

    private final PauseGameController pauseGameController;
    private final PauseGameViewModel pauseGameViewModel;

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;

    // board
    final JPanel board = new JPanel();
    final JTextField[][] box = new JTextField[4][4];

    // move inputs
    private final JTextField rowInputField = new JTextField(1);
    private final JTextField columnInputField = new JTextField(1);
    private final JTextField valueInputField = new JTextField(1);
    private final JButton makeMove;

    // other game options
    private final JButton endGame;
    private final JButton pauseGame;
    private final JLabel lives;
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
        EasyGameViewModel easyGameViewModel1 = new EasyGameViewModel();


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

        // DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
        UserMoveOutputBoundary easyGamePresenter= new EasyGamePresenter(viewManagerModel, easyGameViewModel1);
        UserMoveInteractor userMoveInteractor = new UserMoveInteractor(userDataAccessObject, easyGamePresenter);
        EasyGameController easyGameController1 = new EasyGameController(userMoveInteractor);


        EasyGameView easyGameView = new EasyGameView(easyGameViewModel1, easyGameController1, null, pauseGameViewModel, null, new EndGameViewModel());
        views.add(easyGameView, easyGameView.viewName);

        //easyGameView.setVisible(true);

        // DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
        viewManagerModel.setActiveViewName(easyGameView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

    public EasyGameView(EasyGameViewModel easyGameViewModel, EasyGameController easyGameController,
                        PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                        EndGameController endGameController, EndGameViewModel endGameViewModel) {
        this.easyGameController = easyGameController;
        this.easyGameViewModel = easyGameViewModel;
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel = pauseGameViewModel;
        this.endGameController = endGameController;
        this.endGameViewModel = endGameViewModel;

        easyGameViewModel.addPropertyChangeListener(this);

        board.setLayout(new GridLayout(4, 4));
        board.setAlignmentX(Component.RIGHT_ALIGNMENT);
        board.setSize(500, 500);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JTextField number = new JTextField();
                number.setHorizontalAlignment(JTextField.CENTER);
                number.setFont(new Font("Arial", Font.PLAIN, 20));
                box[row][col] = number;
                board.add(number);
            }
        }
        this.add(board);

        LabelTextPanel rowInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.ROW_LABEL), rowInputField);
        rowInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(rowInfo);

        LabelTextPanel columnInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.COLUMN_LABEL), columnInputField);
        columnInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(columnInfo);

        LabelTextPanel valueInfo = new LabelTextPanel(
                new JLabel(EasyGameViewModel.VALUE_LABEL), valueInputField);
        valueInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(valueInfo);

        lives = new JLabel();
        lives.setText("Lives: ");
        lives.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(lives);

        JLabel timer = new JLabel();
        timer.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timer);

        JPanel buttons = new JPanel();

        endGame = new JButton(easyGameViewModel.END_GAME_BUTTON_LABEL);
        buttons.add(endGame);

        pauseGame = new JButton(easyGameViewModel.PAUSE_GAME_BUTTON_LABEL);
        buttons.add(pauseGame);

        makeMove = new JButton(easyGameViewModel.MAKE_MOVE_BUTTON_LABEL);
        buttons.add(makeMove);
        this.add(buttons);

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
                            EasyGameState currentState = easyGameViewModel.getState();
                            easyGameController.execute(
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

    public void actionPerformed(ActionEvent e) {


    }

    public void propertyChange(PropertyChangeEvent evt) {

    }
}