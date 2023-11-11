package view;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView  extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "menu view";

    private final MenuViewModel menuViewModel;

    private final MenuController menuController;

    // buttons on menu
    private final JButton loadgame;
    private final JButton newgame;
    private final JButton leaderboard;
    private final JButton pastgames;


    public MenuView(MenuController menuController, MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;

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
                            // loadgameController.execute();
                        }
                    }
                }
        );

        newgame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loadgame)) {
                            // newgameController.execute();
                        }
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loadgame)) {
                            // leaderboardController.execute();
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
