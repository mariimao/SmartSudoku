package view;

import app.LoginUseCaseFactory;
import app.PausedGameUseCaseFactory;
import app.SignupUseCaseFactory;
import app.StartUseCaseFactory;
import data_access.UserDAO;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGameState;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartPresenter;
import interface_adapter.start.StartViewModel;
import use_case.menu.MenuInteractor;
import use_case.start.StartInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// when the game is paused I want it to move from the GameView to this one
// It should have two buttons, "Go Back to Menu", "Log Out", "Resume Game
// "Go Back to Menu" - takes them back to the menu
// "Resume Game" - takes them back to the game they are playing
// "Log Out" - takes them back to SignUp View

public class PausedGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Game Paused";
    private final PauseGameViewModel pauseGameViewModel;
    private final StartViewModel startViewModel;
    private final MenuViewModel menuViewModel;
    private final ResumeGameViewModel resumeGameViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StartController startController;
    private final MenuController menuController;
    private final ResumeGameController resumeGameController;
    final JButton backToMenu;
    final JButton logOut;
    final JButton resumeGame;

    public static void main(String[] args) {
        //TODO: for testing
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        StartViewModel startViewModel = new StartViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        CancelViewModel cancelViewModel = new CancelViewModel();
        PauseGameViewModel pauseGameViewModel = new PauseGameViewModel();
        ResumeGameViewModel resumeGameViewModel = new ResumeGameViewModel();
        MenuViewModel menuViewModel = new MenuViewModel();


        // testing userDAO
        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, cancelViewModel, startViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, menuViewModel, cancelViewModel, startViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        StartView startView = StartUseCaseFactory.create(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
        views.add(startView, startView.viewName);

        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, userDataAccessObject);
        views.add(pausedGameView, pausedGameView.viewName);

        viewManagerModel.setActiveViewName(pausedGameView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


    }

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

        JLabel title = new JLabel("Paused Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        backToMenu = new JButton(PauseGameViewModel.BACK_TO_MENU_BUTTON_LABEL);
        buttons.add(backToMenu);
        logOut = new JButton(PauseGameViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        resumeGame = new JButton(PauseGameViewModel.RESUME_GAME_BUTTON_LABEL); //TODO: has to be implemented after the game use case is done or cut altogether
        buttons.add(resumeGame);

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
                            resumeGameController.execute(resumeGameState.getUser());
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
