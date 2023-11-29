package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EndGameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "End Game";

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;

    private final ViewManagerModel viewManagerModel;

    private final MenuController menuController;
    private final MenuViewModel menuViewModel;

    private final LeaderboardController leaderboardController;
    private final LeaderboardViewModel leaderboardViewModel;

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

        JLabel title = new JLabel("End Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        menu = new JButton(EndGameViewModel.MENU_BUTTON_LABEL);
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(menu);

        leaderboard = new JButton(EndGameViewModel.LEADERBOARD_BUTTON_LABEL);
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
