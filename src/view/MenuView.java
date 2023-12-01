package view;

import entity.user.User;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGameState;
import interface_adapter.resume_game.ResumeGameViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private final LoginViewModel loginViewModel;

    // buttons on menu
    private final JButton loadgame;
    private final JButton newgame;
    private final JButton leaderboard;
    private final JButton pastgames;

    private final JButton cancel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    public MenuView(MenuController menuController, MenuViewModel menuViewModel, ResumeGameController resumeGameController,
                    ResumeGameViewModel resumeGameViewModel, NewGameViewModel newGameViewModel, NewGameController newGameController,
                    LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController, LoginViewModel loginViewModel) {
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;
        this.resumeGameController = resumeGameController;
        this.resumeGameViewModel = resumeGameViewModel;
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;
        this.loginViewModel = loginViewModel;

        menuViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        loadgame = new CustomButton(menuViewModel.LOAD_BUTTON_LABEL, darkblue, white);
        loadgame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(loadgame);

        newgame = new CustomButton(menuViewModel.NEW_BUTTON_LABEL, darkblue, white);
        newgame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(newgame);

        leaderboard = new CustomButton(menuViewModel.LEADERBOARD_BUTTON_LABEL, darkblue, white);
        leaderboard.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(leaderboard);

        pastgames = new CustomButton(menuViewModel.PAST_GAMES_BUTTON_LABEL, darkblue, white);
        pastgames.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(pastgames);

        cancel = new CustomButton(menuViewModel.CANCEL_BUTTON_LABEL, darkblue, white);
        cancel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(cancel);

        loadgame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loadgame)) {
                            ResumeGameState resumeGameState = resumeGameViewModel.getState();
                            resumeGameController.execute(resumeGameState.getUserName());
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
                            newGameState.setUserName(loginViewModel.getLoginState().getUsername());
                            int difficulty = newGameState.getDifficulty();
                            newGameController.execute(newGameState.getUserName(), difficulty);
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
                            leaderboardController.execute("mary", sortingMethod, false, false); // false from menu
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

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {

                            setVisible(false);
                            cancel.setVerifyInputWhenFocusTarget( false );
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
