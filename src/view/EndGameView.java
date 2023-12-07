package view;

import entity.Scores;
import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.play_game.PlayGameState;
import interface_adapter.play_game.PlayGameViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the EndGameView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */

public class EndGameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "End Game";
    final JButton menu;
    final JButton leaderboard;
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
    private final PlayGameState finalState;
    private JLabel score;

    /**
     * Constructor for End Game View
     *
     * @param menuViewModel         the view model for the menu usecase, is a MenuViewModel object
     * @param leaderboardController the controller for the leaderboard, is LeaderboardController object
     * @param leaderboardViewModel  the view model for the leaderboard, is LeaderboardViewModel object
     * @param endGameViewModel      the view model for end game, is EndGameViewModel object
     * @param endGameController     the controller for end game, is EndGameController object
     * @param menuController        the controller for menu, is MenuController object
     * @param playGameViewModel     the view model for play game, is PlayGameViewModel object
     * @param viewManagerModel      the view manager model, is ViewManagerModel object
     */


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
        Scores scores = new Scores(finalState.getTimePlayed(), finalState.getSpacesLeft(), finalState.getLives(), finalState.isCompleted());
        int numScore = scores.getScores();
        JLabel score = new JLabel();
        score.setText("SCORE: ".concat(String.valueOf(numScore)));
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        score.setFont(new Font("Helvetica", Font.BOLD, 50));
        score.setForeground(darkblue);
        score.setBorder(new CompoundBorder(score.getBorder(), new EmptyBorder(10, 40, 10, 40)));

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
