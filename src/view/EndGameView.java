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
