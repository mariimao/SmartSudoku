package view;

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

/**
 * View for the MenuView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
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

    private final JButton cancel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    /**
     * Constructor for Login View
     * @param loginViewModel the view model for login usecase, is a LoginViewModel object
     * @param menuViewModel the view model for the menu usecase, is a MenuViewModel object
     * @param resumeGameViewModel the view model for the resume usecase, is a ResumeGameViewModel object
     * @param leaderboardController the controller for the leaderboard, is LeaderboardController object
     * @param leaderboardViewModel the view model for the leaderboard, is LeaderboardViewModel object
     * @param menuController the controller for the menu, is MenuController object
     * @param newGameController the controller for new game, is NewGameController object
     * @param newGameViewModel the view model for new game, is NewGameViewModel object
     * @param resumeGameController the controller for resuming the game, is ResumeGameController object
     */
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
                            leaderboardState.setUsername(username);
                            leaderboardViewModel.setLeaderboardState(leaderboardState);
                            leaderboardController.execute(username, sortingMethod, false, false); // false from menu
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

    /**
     * Records the action performed
     * @param e the action that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Records and notifies of any property change
     * @param evt the propertychange event that is fired by the viewmodel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
