import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.make_move.MakeMoveViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyViewModel;
import interface_adapter.start.StartViewModel;
import view.MenuView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class UseCaseTestObjects {

    private final StartViewModel startViewModel;
    private final LoginViewModel loginViewModel;
    private EndGameViewModel endGameViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private PauseGameViewModel pauseGameViewModel;
    private ResumeGameViewModel resumeGameViewModel;
    private MenuViewModel menuViewModel;
    private NewGameViewModel newGameViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private PlayGameViewModel playGameViewModel;
    private SpotifyViewModel spotifyViewModel;
    private MakeMoveViewModel makeMoveViewModel;

    public UseCaseTestObjects() {
        JFrame application = new JFrame("SudokuScramble");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        startViewModel = new StartViewModel();
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        pauseGameViewModel = new PauseGameViewModel();
        resumeGameViewModel = new ResumeGameViewModel();
        menuViewModel = new MenuViewModel();
        newGameViewModel = new NewGameViewModel();
        leaderboardViewModel = new LeaderboardViewModel();
        endGameViewModel = new EndGameViewModel();
        playGameViewModel = new PlayGameViewModel();
        spotifyViewModel = new SpotifyViewModel();
        makeMoveViewModel = new MakeMoveViewModel();
    }

    public StartViewModel getStartViewModel() {
        return startViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public EndGameViewModel getEndGameViewModel() {
        return endGameViewModel;
    }

    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }

    public PauseGameViewModel getPauseGameViewModel() {
        return pauseGameViewModel;
    }

    public ResumeGameViewModel getResumeGameViewModel() {
        return resumeGameViewModel;
    }

    public SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    public LeaderboardViewModel getLeaderboardViewModel() {
        return leaderboardViewModel;
    }

    public NewGameViewModel getNewGameViewModel() {
        return newGameViewModel;
    }


    public MakeMoveViewModel getMakeMoveViewModel() {
        return makeMoveViewModel;
    }

    public PlayGameViewModel getPlayGameViewModel() {
        return playGameViewModel;
    }

    public SpotifyViewModel getSpotifyViewModel() {
        return spotifyViewModel;
    }
}
