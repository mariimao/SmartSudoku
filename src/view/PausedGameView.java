package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGameState;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

// when the game is paused I want it to move from the GameView to this one
// It should have two buttons, "Go Back to Menu", "Log Out", "Resume Game
// "Go Back to Menu" - takes them back to the menu
// "Resume Game" - takes them back to the game they are playing
// "Log Out" - takes them back to SignUp View

/**
 * View for the PauseGameView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class PausedGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "GAME PAUSED";
    final JButton backToMenu;
    final JButton logOut;
    final JButton resumeGame;
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;
    private final PauseGameViewModel pauseGameViewModel;
    private final StartViewModel startViewModel;
    private final MenuViewModel menuViewModel;
    private final ResumeGameViewModel resumeGameViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StartController startController;
    private final MenuController menuController;
    private final ResumeGameController resumeGameController;

    /**
     * Constructor for PauseGame View
     *
     * @param resumeGameController the controller for ResumeGame, is ResumeGameController object
     * @param menuController       the controller for menu use case, is MenuController object
     * @param pauseGameViewModel   the view model for pause, is PauseGameViewModel object
     * @param resumeGameViewModel  the view model for resumegame, is ResumeGameViewModel object
     * @param menuViewModel        the view model for the menu, is MenuViewModel object
     * @param startController      the controller for the start, is StartController object
     * @param startViewModel       the view model for the start, is StartViewModel object
     * @param viewManagerModel     the view manager model, is ViewManagerModel object
     */
    public PausedGameView(PauseGameViewModel pauseGameViewModel,
                          StartViewModel startViewModel,
                          MenuViewModel menuViewModel,
                          ViewManagerModel viewManagerModel,
                          ResumeGameViewModel resumeGameViewModel,
                          StartController startController,
                          MenuController menuController, ResumeGameController resumeGameController) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.startViewModel = startViewModel;
        this.menuViewModel = menuViewModel;
        this.resumeGameViewModel = resumeGameViewModel;

        this.viewManagerModel = viewManagerModel;
        this.startController = startController;
        this.menuController = menuController;
        this.resumeGameController = resumeGameController;

        pauseGameViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel("Paused Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10, 40, 10, 40)));
        this.add(title);

        JPanel buttons = new JPanel();
        backToMenu = new CustomButton(PauseGameViewModel.BACK_TO_MENU_BUTTON_LABEL, darkblue, white);
        backToMenu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(backToMenu);

        logOut = new CustomButton(PauseGameViewModel.LOGOUT_BUTTON_LABEL, darkblue, white);
        logOut.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(logOut);

        resumeGame = new CustomButton(PauseGameViewModel.RESUME_GAME_BUTTON_LABEL, darkblue, white); //TODO: has to be implemented after the game use case is done or cut altogether
        resumeGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(resumeGame);

        this.add(buttons);

        logOut.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            startController.execute("Login");
                        }
                    }
                }
        );

        backToMenu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backToMenu)) {
                            menuController.execute();
                        }
                    }
                }
        );

        resumeGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(resumeGame)) {
                            ResumeGameState resumeGameState = resumeGameViewModel.getState();
                            try {
                                resumeGameController.execute(resumeGameState.getUserName());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
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

    }

}
