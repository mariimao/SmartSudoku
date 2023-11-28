package app;

import data_access.UserDAO;
import entity.user.CommonUser;
import entity.board.EasyBoard;
import entity.board.HardBoard;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("SudokuScramble");
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
        PauseGameViewModel pauseGameViewModel = new PauseGameViewModel();
        ResumeGameViewModel resumeGameViewModel = new ResumeGameViewModel();
        MenuViewModel menuViewModel = new MenuViewModel();
        NewGameViewModel newGameViewModel = new NewGameViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        EasyGameViewModel easyGameViewModel = new EasyGameViewModel();
        EndGameViewModel endGameViewModel = new EndGameViewModel();
        PlayGameViewModel playGameViewModel = new PlayGameViewModel();


        // testing userDAO
        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StartView startView = StartUseCaseFactory.create(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
        views.add(startView, startView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, startViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, menuViewModel, startViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        // TODO: Update this when you add more views
        MenuView menuView = MenuUseCaseFactory.create(viewManagerModel, menuViewModel,resumeGameViewModel, loginViewModel, newGameViewModel, userDataAccessObject, leaderboardViewModel);
        views.add(menuView, menuView.viewName);

        PausedGameView pausedGameView = PausedGameUseCaseFactory.create(viewManagerModel, pauseGameViewModel, startViewModel, menuViewModel, signupViewModel, loginViewModel, resumeGameViewModel, userDataAccessObject);
        views.add(pausedGameView, pausedGameView.viewName);

        NewGameView newGameView = NewGameUseCaseFactory.create(viewManagerModel,newGameViewModel, userDataAccessObject, playGameViewModel);
        views.add(newGameView, newGameViewModel.getViewName());

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(viewManagerModel,leaderboardViewModel, userDataAccessObject);
        views.add(leaderboardView, leaderboardViewModel.getViewName());

        BoardView boardView = BoardUseCaseFactory.create(viewManagerModel, easyGameViewModel, pauseGameViewModel, endGameViewModel, leaderboardViewModel, menuViewModel, startViewModel, playGameViewModel, userDataAccessObject );
        views.add(boardView, "Board View");  // TODO: link neccessary views and viewmodels

        viewManagerModel.setActiveViewName(startView.viewName);  //TODO: change back to startView.viewName
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


        //userDataAccessObject.deleteAll(); //for testing
        Map<LocalTime, Integer> sampleScores = new HashMap<>();
        sampleScores.put(LocalTime.now(), 4);
        sampleScores.put(LocalTime.of(12, 30, 1), 3);
        CommonUser user1 = new CommonUser("user1", "pass2", sampleScores);
        userDataAccessObject.addUser(user1);
        sampleScores.put(LocalTime.of(12, 31, 1), 4);
        CommonUser user2 = new CommonUser("user2", "pass", sampleScores);
        userDataAccessObject.addUser(user2);
//        System.out.println(userDataAccessObject.toString());
//        System.out.println(user1.getName());
//        System.out.println(user1.getPassword());
//        System.out.println(user1.getScores());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getScores());

//        userDataAccessObject.delete("user1");
//        System.out.println(userDataAccessObject.toString());

        // board generation
        EasyBoard easyTester = new EasyBoard();
        System.out.println(easyTester);

        HardBoard hardTester = new HardBoard();
        System.out.println(hardTester);


    }
}
