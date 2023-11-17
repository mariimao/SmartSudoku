package view;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.start.StartInteractor;
import use_case.start.StartOutputBoundary;
import use_case.start.StartUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.util.Map;

// when the game is paused I want it to move from the GameView to this one
// It should have two buttons, "Go Back to Menu", "Log Out", "Resume Game
// "Go Back to Menu" - takes them back to the menu
// "Resume Game" - takes them back to the game they are playing
// "Log Out" - takes them back to SignUp View

public class PauseGameView extends JPanel implements ActionListener, PropertyChangeListener {
    // TODO: FINISH
    public final String viewName = "Game Paused";
    private final PauseGameViewModel pauseGameViewModel;

    final JButton backToMenu;
    final JButton logOut;
    final JButton resumeGame;
    private final PauseGameController pauseGameController;
    private final SignupUserDataAccessInterface signupUserDataAccessInterface;
    private final ViewManagerModel viewManagerModel;
    private final StartOutputBoundary startPresenter;
    private final UserFactory userFactory;
    private final Map<LocalTime, Integer> scores;
    private final StartUserDataAccessInterface startUserDataAccessInterface;
    private final MenuUserDataAccessInterface menuUserDataAccessInterface;
    private final MenuOutputBoundary menuPresenter;

    public static void main(String[] args) {
        //TODO: for testing
        // PauseGameView pauseGameView = new PauseGameView();
    }

    public PauseGameView(PauseGameViewModel pauseGameViewModel,
                         PauseGameController pauseGameController,
                         SignupUserDataAccessInterface signupUserDataAccessInterface,
                         ViewManagerModel viewManagerModel, StartUserDataAccessInterface startUserDataAccessInterface,
                         StartOutputBoundary startPresenter,
                         UserFactory userFactory,
                         Map<LocalTime, Integer> scores, MenuUserDataAccessInterface menuUserDataAccessInterface, MenuOutputBoundary menuPresenter) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.pauseGameController = pauseGameController;
        this.signupUserDataAccessInterface = signupUserDataAccessInterface;
        this.viewManagerModel = viewManagerModel;
        this.startUserDataAccessInterface = startUserDataAccessInterface;
        this.startPresenter = startPresenter;
        this.userFactory = userFactory;
        this.scores = scores;
        this.menuUserDataAccessInterface = menuUserDataAccessInterface;
        this.menuPresenter = menuPresenter;
        this.pauseGameViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Paused Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        backToMenu = new JButton(pauseGameViewModel.BACK_TO_MENU_BUTTON_LABEL);
        buttons.add(backToMenu);
        logOut = new JButton(pauseGameViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        resumeGame = new JButton(pauseGameViewModel.RESUME_GAME_BUTTON_LABEL); //TODO: has to be implemented after the game use case is done or cut altogether
        buttons.add(resumeGame);

        logOut.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            StartInteractor startInteractor = new StartInteractor(startUserDataAccessInterface, startPresenter);
                            StartController startController = new StartController(startInteractor);
                            StartView startView = new StartView(startController, new StartViewModel());
                            PauseGameView.this.viewManagerModel.setActiveViewName(startView.viewName);
                            PauseGameView.this.viewManagerModel.firePropertyChanged();
                        }

                        else if (evt.getSource().equals(backToMenu)) {
                            MenuInteractor menuInteractor = new MenuInteractor(menuUserDataAccessInterface, menuPresenter);
                            MenuController menuController = new MenuController(menuInteractor);
                            MenuView menuView = new MenuView(menuController, new MenuViewModel());
                            PauseGameView.this.viewManagerModel.setActiveViewName(menuView.viewName);
                            PauseGameView.this.viewManagerModel.firePropertyChanged();
                        }

                        else {
                            // TODO: IMPLEMENT RESUME GAME
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
