package view;

import app.*;
import data_access.UserDAO;
import entity.user.CommonUserFactory;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGameState;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelViewModel;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuView  extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "menu view";

    private final MenuViewModel menuViewModel;

    private final MenuController menuController;
    private final ResumeGameController resumeGameController;
    private final ResumeGameViewModel resumeGameViewModel;
    private final NewGameViewModel newGameViewModel;
    private final NewGameController newGameController;

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;

    // buttons on menu
    private final JButton loadgame;
    private final JButton newgame;
    private final JButton leaderboard;
    private final JButton pastgames;

    public MenuView(MenuController menuController, MenuViewModel menuViewModel, ResumeGameController resumeGameController,
                    ResumeGameViewModel resumeGameViewModel, NewGameViewModel newGameViewModel, NewGameController newGameController,
                    LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;
        this.resumeGameController = resumeGameController;
        this.resumeGameViewModel = resumeGameViewModel;
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;

        menuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(StartViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        loadgame = new JButton(menuViewModel.LOAD_BUTTON_LABEL);
        buttons.add(loadgame);

        newgame = new JButton(menuViewModel.NEW_BUTTON_LABEL);
        buttons.add(newgame);

        leaderboard = new JButton(menuViewModel.LEADERBOARD_BUTTON_LABEL);
        buttons.add(leaderboard);

        pastgames = new JButton(menuViewModel.PAST_GAMES_BUTTON_LABEL);
        buttons.add(pastgames);

        loadgame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loadgame)) {
                            ResumeGameState resumeGameState = resumeGameViewModel.getState();
                            resumeGameController.execute(resumeGameState.getUser());
                        }
                    }
                }
        );

        newgame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(newgame)) {
                            NewGameState newGameState = newGameViewModel.getState();
                            User user = newGameState.getUser();
                            int difficulty = newGameState.getDifficulty();
                            newGameController.execute(user, difficulty);
                        }
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(leaderboard)) {
                            LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
                            String username = menuViewModel.getMenuState().getUsername(); // get username from menu
                            String sortingMethod = leaderboardState.getSortingMethod();
                            leaderboardController.execute(username, sortingMethod, false); // false from menu
                        }
                    }
                }
        );

        pastgames.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loadgame)) {
                            // pastgamesController.execute();
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
