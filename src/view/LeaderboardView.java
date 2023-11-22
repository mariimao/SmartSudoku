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
    private final CancelController cancelController;


    // text input
    private final JButton cancel;
    private final JComboBox<String> sortingMethod;

    private final JButton userView;


    public LeaderboardView(MenuViewModel menuViewModel, LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController, CancelController cancelController) {
        this.menuViewModel = menuViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;
        this.cancelController = cancelController;


        this.leaderboardViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);

        JLabel lbl = new JLabel(LeaderboardViewModel.SORT_BY_CHOICE_LABEL);
        String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3", "CHOICE 4",
                "CHOICE 5", "CHOICE 6" };
        sortingMethod = new JComboBox<String>(choices);


        JPanel buttons = new JPanel();

        cancel = new JButton(LeaderboardViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        userView = new JButton(LeaderboardViewModel.USER_BUTTON_LABEL);
        buttons.add(userView);

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            cancelController.execute();
                        }
                    }
                }
        );

        userView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            // leaderboardController.execute();
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


