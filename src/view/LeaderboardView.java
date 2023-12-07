package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.util.SortedMap;

/**
 * View for the LeaderboardView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "leaderboard view";

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;

    private final MenuViewModel menuViewModel;

    private final JLabel choices;
    // buttons + options
    private final JComboBox<String> sortingMethod;

    private final JButton userView;

    private final JButton menu;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    /**
     * Constructor for Leaderboard View
     *
     * @param menuViewModel         the view model for the menu usecase, is a MenuViewModel object
     * @param leaderboardController the controller for the leaderboard, is LeaderboardController object
     * @param leaderboardViewModel  the view model for the leaderboard, is LeaderboardViewModel object
     */
    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController, MenuViewModel menuViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;
        this.menuViewModel = menuViewModel;


        this.leaderboardViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10, 40, 10, 40)));
        this.add(title);

        JPanel buttons = new JPanel();
        buttons.setBackground(white);

        userView = new CustomButton(LeaderboardViewModel.USER_BUTTON_LABEL, darkblue, white);
        userView.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(userView);

        menu = new CustomButton(LeaderboardViewModel.MENU_BUTTON_LABEL, darkblue, white);
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(menu);

        choices = new JLabel(LeaderboardViewModel.SORT_BY_CHOICE_LABEL);
        sortingMethod = new JComboBox<String>(LeaderboardViewModel.CHOICES);


        this.add(title);
        this.add(buttons);
        this.add(choices);
        this.add(sortingMethod);


        userView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(userView)) {
                            leaderboardController.execute(leaderboardViewModel.getLeaderboardState().getUsername(),
                                    leaderboardViewModel.getLeaderboardState().getSortingMethod(), true, false);
                        }
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
                        if (e.getStateChange() == ItemEvent.SELECTED) {
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
        if (evt.getPropertyName().equals("leaderboard")) {
            LeaderboardState state = (LeaderboardState) evt.getNewValue();
            leaderboardViewModel.setLeaderboardState(state);


            SortedMap<Object, Object> leaderboard = state.getLeaderboard();

            JTable table;
            JLabel time = new JLabel("Updated as of " + LocalTime.now());
            this.add(time);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Rank", "Users"}, 0);
            if (leaderboard != null) {
                for (Object i : leaderboard.keySet()) {
                    model.addRow(new Object[]{i, leaderboard.get(i)});
                }
                table = new JTable(model);
                this.add(table);
            } else {
                JLabel labels = new JLabel(LeaderboardViewModel.NO_SCORES_LABEL);
                this.add(labels);
            }
        }
    }
}