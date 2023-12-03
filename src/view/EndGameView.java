package view;

import app.*;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.Scores;
import entity.board.GameState;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGamePresenter;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.make_move.MakeMoveController;
import interface_adapter.make_move.MakeMovePresenter;
import interface_adapter.make_move.MakeMoveViewModel;
import interface_adapter.menu.MenuController;
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
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;
import use_case.end_game.EndGameInteractor;
import use_case.make_move.MakeMoveInteractor;
import use_case.pause_game.PauseGameInteractor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EndGameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "End Game";

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;

    private final ViewManagerModel viewManagerModel;

    private final MenuController menuController;
    private final MenuViewModel menuViewModel;

    private final LeaderboardController leaderboardController;
    private final LeaderboardViewModel leaderboardViewModel;

    private final PlayGameViewModel playGameViewModel;


    final JButton menu;
    final JButton leaderboard;

    private JLabel score;
    private final PlayGameState finalState;

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
        MakeMoveViewModel makeMoveViewModel1 = new MakeMoveViewModel();


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

        EndGameController endGameController1 = new EndGameController(new EndGameInteractor(userDataAccessObject, new EndGamePresenter(leaderboardViewModel, menuViewModel, endGameViewModel, viewManagerModel)));
        PauseGameController pauseGameController1 = new PauseGameController(new PauseGameInteractor(userDataAccessObject, new PauseGamePresenter(startViewModel, menuViewModel, pauseGameViewModel, viewManagerModel)));
        MakeMoveController makeMoveController1 = new MakeMoveController(new MakeMoveInteractor(userDataAccessObject, new MakeMovePresenter(makeMoveViewModel1, viewManagerModel)));

        EndGameView endGameView = EndGameUseCaseFactory.create(viewManagerModel, endGameViewModel, userDataAccessObject, menuViewModel, leaderboardViewModel, startViewModel, signupViewModel, loginViewModel);
        views.add(endGameView, endGameView.viewName);

        viewManagerModel.setActiveViewName(endGameView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public EndGameView(EndGameViewModel endGameViewModel,
                       EndGameController endGameController,
                       ViewManagerModel viewManagerModel,
                       MenuController menuController,
                       MenuViewModel menuViewModel,
                       LeaderboardController leaderboardController,
                       LeaderboardViewModel leaderboardViewModel,
                       PlayGameViewModel playGameViewModel) {
        this.endGameViewModel = endGameViewModel;
        this.endGameController = endGameController;
        this.viewManagerModel = viewManagerModel;
        this.menuController = menuController;
        this.menuViewModel = menuViewModel;
        this.leaderboardController = leaderboardController;
        this.leaderboardViewModel = leaderboardViewModel;
        this.playGameViewModel = playGameViewModel;

        endGameViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        this.finalState = playGameViewModel.getState();
        Scores scores = new Scores(finalState.getTime(), finalState.getSpacesLeft(), finalState.getLives(), finalState.isCompleted());
        int numScore = scores.getScores();
        JLabel score = new JLabel();
        score.setText("SCORE: ".concat(String.valueOf(numScore)));
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        score.setFont(new Font("Helvetica", Font.BOLD, 50));
        score.setForeground(darkblue);
        score.setBorder(new CompoundBorder(score.getBorder(), new EmptyBorder(10,40,10,40)));

        JPanel buttons = new JPanel();

        menu = new CustomButton(EndGameViewModel.MENU_BUTTON_LABEL, darkblue, white);
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(menu);

        leaderboard = new CustomButton(EndGameViewModel.LEADERBOARD_BUTTON_LABEL, darkblue, white);
        leaderboard.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(leaderboard);


        menu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(menu)) {
                            menuController.execute();
                        }
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(leaderboard)) {
                            LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
                            String username = endGameViewModel.getState().getUser(); // get username from menu
                            String sortingMethod = leaderboardState.getSortingMethod();
                            leaderboardController.execute(username, sortingMethod, false, false); // false from menu
                        }
                    }
                }

        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(score);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
