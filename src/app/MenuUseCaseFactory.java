package app;

import data_access.UserDAO;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGamePresenter;
import interface_adapter.resume_game.ResumeGameViewModel;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import use_case.resume_game.ResumeGameDataAccessInterface;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInteractor;
import use_case.resume_game.ResumeGameOutputBoundary;
import view.MenuView;

import javax.swing.*;
import java.io.IOException;

public class MenuUseCaseFactory {
    private MenuUseCaseFactory() {}

    public static MenuView create(
            ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, ResumeGameViewModel resumeGameViewModel, LoginViewModel loginViewModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject) {

        try {
            // TODO: Update these lines so that it includes the viewmodels that include the views for the games, leaderboard, etc.

            MenuController menuController = createUserSignupUseCase(viewManagerModel, menuViewModel, userDataAccessObject);
            ResumeGameController resumeGameController = createUserResumeCase(viewManagerModel, resumeGameViewModel, loginViewModel, userDataAccessObject);
            NewGameController newGameController = creatUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
            return new MenuView(menuController, menuViewModel, resumeGameController, resumeGameViewModel, newGameViewModel, newGameController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NewGameController creatUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }

    private static MenuController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                           MenuViewModel menuViewModel, UserDAO userDataAccessObject) throws IOException {

        MenuOutputBoundary menuOutputBoundary = new MenuPresenter();

        MenuInteractor menuInteractor = new MenuInteractor(
                userDataAccessObject, menuOutputBoundary);

        return new MenuController(menuInteractor);
    }
    private static ResumeGameController createUserResumeCase(ViewManagerModel viewManagerModel,
                                                             ResumeGameViewModel resumeGameViewModel,
                                                             LoginViewModel loginViewModel,
                                                             ResumeGameDataAccessInterface resumeGameDataAccessInterface) {
        // Going into the LoginState to retrieve the player's username
        String username = loginViewModel.getLoginState().getUsername();  // ASSUMPTION: they have to already be logged in to hit the resume button
        User user = resumeGameDataAccessInterface.get(username);

        ResumeGameOutputBoundary resumeGamePresenter = new ResumeGamePresenter(resumeGameViewModel, viewManagerModel);
        ResumeGameInputBoundary resumeGameInteractor = new ResumeGameInteractor(resumeGameDataAccessInterface, resumeGamePresenter);
        return new ResumeGameController(resumeGameInteractor);
    }

}
