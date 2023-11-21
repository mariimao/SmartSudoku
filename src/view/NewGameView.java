package view;

import entity.user.User;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "new game view";
    private final NewGameViewModel newGameViewModel;
    private final NewGameController newGameController;
    // TODO: the viewModel and controller for the board view use case need to be added
    private final JButton createEasyGame;
    private final JButton createHardGame;

    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController) {
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;
        JLabel title = new JLabel("Choose Game Difficulty");
        JPanel buttons = new JPanel();
        createEasyGame = new JButton("Easy Game");
        buttons.add(createEasyGame);
        createHardGame = new JButton("Hard Game");
        buttons.add(createHardGame);

        createEasyGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createEasyGame)) {
                            NewGameState newGameState = newGameViewModel.getState();
                            User user = newGameState.getUser();
                            int difficulty = newGameState.getDifficulty();
                            newGameController.execute(user, difficulty);
                        }
                    }
                }
        );

        createHardGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createHardGame)) {
                            NewGameState newGameState = newGameViewModel.getState();
                            newGameState.setDifficulty(2);
                            User user = newGameState.getUser();
                            int difficulty = newGameState.getDifficulty();
                            newGameController.execute(user, difficulty);
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
