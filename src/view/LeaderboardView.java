package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.SortedMap;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "leaderboard view";

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;


    // buttons + options
    private final JComboBox<String> sortingMethod;

    private final JButton userView;

    private final JButton menu;


    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
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
                            LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
                            String user = leaderboardState.getUsername();
                            String sortingMethod = leaderboardState.getSortingMethod();
                            leaderboardController.execute(user, sortingMethod, false, true);
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

        // TODO: reorganize this later
        JTable table;
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Rank", "Users" }, 0);
        if (LeaderboardViewModel.LEADERBOARD!=null) {
            for (Object i : LeaderboardViewModel.LEADERBOARD.keySet()) {
                model.addRow(new Object[]{i, LeaderboardViewModel.LEADERBOARD.get(i)});
            }
            table = new JTable(model);
            this.add(table);
        } else {
            JLabel labels = new JLabel("no users");
            this.add(labels);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LeaderboardState state = (LeaderboardState) evt.getNewValue();
    }
}


