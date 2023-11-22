package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "leaderboard view";

    private final MenuViewModel menuViewModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;

    // buttons + options
    //TODO: back button
    private final JComboBox<String> sortingMethod;

    private final JButton userView;

    private final JButton menu;


    public LeaderboardView(MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
        this.menuViewModel = menuViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;


        this.leaderboardViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        userView = new JButton(LeaderboardViewModel.USER_BUTTON_LABEL);
        buttons.add(userView);

        menu = new JButton(LeaderboardViewModel.MENU_BUTTON_LABEL);
        buttons.add(menu);


        userView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(cancel)) {
//                            // leaderboardController.execute();
//                        }
                    }
                }
        );

        menu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(menu)) {
                            //menuView.
                        }
                    }
                }
        );

        JLabel lbl = new JLabel(LeaderboardViewModel.SORT_BY_CHOICE_LABEL);
        String[] choices = { "Rank" };
        sortingMethod = new JComboBox<String>(choices);

        sortingMethod.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(sortingMethod)) {
                            LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
                            leaderboardState.setSortingMethod((String) sortingMethod.getSelectedItem());
                            leaderboardViewModel.setLeaderboardState(leaderboardState);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(sortingMethod);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LeaderboardState state = (LeaderboardState) evt.getNewValue();
    }
}


