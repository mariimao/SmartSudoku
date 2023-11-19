package view;

import data_access.UserDAO;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.signup.SignupViewModel;
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
    private final ViewManagerModel viewManagerModel;
    private final StartController startController;
    private final MenuController menuController;
    final JButton backToMenu;
    final JButton logOut;
    final JButton resumeGame;

    public static void main(String[] args) {
        //TODO: for testing
        UserDAO userDAO;
        try {
            userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //userDataAccessObject.deleteAll(); //for testing
        Map<LocalTime, Integer> sampleScores = new HashMap<>();
        sampleScores.put(LocalTime.now(), 4);
        sampleScores.put(LocalTime.of(12, 30, 1), 3);
        CommonUser user1 = new CommonUser("user1", "pass2", sampleScores);
        userDAO.addUser(user1);
        sampleScores.put(LocalTime.of(12, 31, 1), 4);
        CommonUser user2 = new CommonUser("user2", "pass", sampleScores);
        userDAO.addUser(user2);
        PauseGameViewModel pauseGameViewModel1 = new PauseGameViewModel();
        StartViewModel startViewModel1 = new StartViewModel();
        SignupViewModel signupViewModel1 = new SignupViewModel();
        LoginViewModel loginViewModel1 = new LoginViewModel();
        MenuViewModel menuViewModel1 = new MenuViewModel();
        ViewManagerModel viewManagerModel1 = new ViewManagerModel();
        StartController startController1 = new StartController(new StartInteractor(userDAO, new StartPresenter(startViewModel1, signupViewModel1, loginViewModel1, viewManagerModel1)));
        MenuController menuController1 = new MenuController(new MenuInteractor(userDAO, new MenuPresenter()));
        PausedGameView pausedGameView = new PausedGameView(pauseGameViewModel1, startViewModel1, menuViewModel1, viewManagerModel1, startController1, menuController1);

        // CREATED ALL NECESSARY PARTS FOR THE APP TO RUN VIEWS

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        // This keeps track of and manages which view is currently showing.
        new ViewManager(views, cardLayout, viewManagerModel1);
        views.add(pausedGameView, pausedGameView.viewName);

        viewManagerModel1.setActiveViewName(pausedGameView.viewName);
        viewManagerModel1.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

    public PausedGameView(PauseGameViewModel pauseGameViewModel,
                          StartViewModel startViewModel,
                          MenuViewModel menuViewModel,
                          ViewManagerModel viewManagerModel,
                          StartController startController,
                          MenuController menuController) {
        this.pauseGameViewModel = pauseGameViewModel;
        this.startViewModel = startViewModel;
        this.menuViewModel = menuViewModel;

        this.viewManagerModel = viewManagerModel;
        this.startController = startController;
        this.menuController = menuController;

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
                            StartView startView = new StartView(startController, new StartViewModel());
                            startController.execute("Login");
                            // PauseGameView.this.viewManagerModel.setActiveViewName(startView.viewName);
                            // PauseGameView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        backToMenu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backToMenu)) {
                            MenuView menuView = new MenuView(menuController, new MenuViewModel());
                            menuController.execute();
                            // PausedGameView.this.viewManagerModel.setActiveViewName(menuView.viewName);
                            // PausedGameView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        resumeGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(resumeGame)) {
                            // TODO: implement when game playing use case is done
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
