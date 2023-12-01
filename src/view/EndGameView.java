package view;

import entity.Scores;
import entity.board.GameState;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

    private JLabel score;
    private final EndGameState finalState;

    final JButton menu;
    final JButton leaderboard;

    public EndGameView(EndGameViewModel endGameViewModel,
                       EndGameController endGameController,
                       ViewManagerModel viewManagerModel,
                       MenuController menuController,
                       MenuViewModel menuViewModel,
                       LeaderboardController leaderboardController,
                       LeaderboardViewModel leaderboardViewModel) {
        this.endGameViewModel = endGameViewModel;
        this.endGameController = endGameController;
        this.viewManagerModel = viewManagerModel;
        this.menuController = menuController;
        this.menuViewModel = menuViewModel;
        this.leaderboardController = leaderboardController;
        this.leaderboardViewModel = leaderboardViewModel;

        endGameViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel("End Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));

        JPanel buttons = new JPanel();

        menu = new CustomButton(EndGameViewModel.MENU_BUTTON_LABEL, darkblue, white);
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(menu);

        leaderboard = new CustomButton(EndGameViewModel.LEADERBOARD_BUTTON_LABEL, darkblue, white);
        leaderboard.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(leaderboard);

        this.finalState = endGameViewModel.getState();
        Scores scores = finalState.getScore();
        int numScore = scores.getScores();
        score = new JLabel();
        score.setText("SCORE: ".concat(String.valueOf(numScore)));
        score.setFont(new Font("Consolas", Font.ITALIC, 20));
        score.setBackground(white);
        score.setForeground(black);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(score);


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
                            String username = menuViewModel.getMenuState().getUsername();
                            String sortingMethod = leaderboardState.getSortingMethod();
                            leaderboardController.execute("mary", sortingMethod, false, false);
                        }
                    }
                }

        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
