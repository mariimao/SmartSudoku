package view;

import entity.user.User;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);

    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController) {
        this.newGameViewModel = newGameViewModel;
        this.newGameController = newGameController;

        newGameViewModel.addPropertyChangeListener(this);
        this.setBackground(white);

        // creating the title
        JLabel title = new JLabel("Choose Game Difficulty");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 90));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        //  creating the buttons
        JPanel buttons = new JPanel();
        createEasyGame = new CustomButton("Easy Game", darkblue, white);
        createEasyGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(createEasyGame);
        createHardGame = new CustomButton("Hard Game", darkblue, white);
        createHardGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
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
