package view;

import interface_adapter.login.LoginState;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameState;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// when the game is paused I want it to move from the GameView to this one
// It should have two buttons, "Go Back to Menu", "Log Out", "Resume Game
// "Go Back to Menu" - takes them back to the menu
// "Resume Game" - takes them back to the game they are playing
// "Log Out" - takes them back to SignUp View

public class PauseGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Game Paused";
    private final PauseGameViewModel pauseGameViewModel;

    final JButton backToMenu;
    final JButton logOut;
    final JButton resumeGame;
    private final PauseGameController pauseGameController;

    public PauseGameView(PauseGameViewModel pauseGameViewModel, PauseGameController pauseGameController) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Paused Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        backToMenu = new JButton(pauseGameViewModel.BACK_TO_MENU_BUTTON_LABES);
        buttons.add(backToMenu);
        logOut = new JButton(pauseGameViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        resumeGame = new JButton(pauseGameViewModel.RESUME_GAME_BUTTON_LABEL);
        buttons.add(resumeGame);

        logOut.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {



                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {


                            PauseGameState currentState = pauseGameViewModel.getState();

                            signupViewModel.execute(
                            );
                        }
                    }
                }
        );

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
