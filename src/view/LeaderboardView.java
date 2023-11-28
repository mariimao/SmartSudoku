package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.spotify.SpotifyState;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.SortedMap;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "leaderboard view";

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;

    private final JLabel choices;
    // buttons + options
    private final JComboBox<String> sortingMethod;

    private final JButton userView;

    private final JButton menu;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;


        this.leaderboardViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel(leaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        JPanel buttons = new JPanel();
        buttons.setBackground(white);

        userView = new CustomButton(leaderboardViewModel.USER_BUTTON_LABEL, darkblue, white);
        userView.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(userView);

        menu = new CustomButton(leaderboardViewModel.MENU_BUTTON_LABEL, darkblue, white);
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(menu);

        choices = new JLabel(leaderboardViewModel.SORT_BY_CHOICE_LABEL);
        sortingMethod = new JComboBox<String>(leaderboardViewModel.CHOICES);


        this.add(title);
        this.add(buttons);
        this.add(choices);
        this.add(sortingMethod);

        JTable table;
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Rank", "Users" }, 0);
        if (LeaderboardViewModel.LEADERBOARD!=null) { //we have scores
            for (Object i : LeaderboardViewModel.LEADERBOARD.keySet()) {
                model.addRow(new Object[]{i, LeaderboardViewModel.LEADERBOARD.get(i)});
            }
            table = new JTable(model);
            this.add(table);
        } else {
            JLabel labels = new JLabel(LeaderboardViewModel.NO_SCORES_LABEL);
            this.add(labels);
        }


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

        sortingMethod.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED) {
                            if (e.getSource() instanceof JComboBox) {
                                JComboBox cb = (JComboBox) e.getSource();
                                String chosenMethod = (String) cb.getSelectedItem();

                                LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
                                leaderboardState.setSortingMethod((String) sortingMethod.getSelectedItem());
                                leaderboardViewModel.setLeaderboardState(leaderboardState);
                            }
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
        LeaderboardState state = (LeaderboardState) evt.getNewValue();
    }
}


