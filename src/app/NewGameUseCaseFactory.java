package app;

import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.resume_game.ResumeGameController;
import interface_adapter.resume_game.ResumeGameViewModel;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.new_game.NewGameDataAccessInterface;
import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInteractor;
import view.MenuView;
import view.NewGameView;

import javax.swing.*;
import java.io.IOException;

public class NewGameUseCaseFactory {
    private NewGameUseCaseFactory() {}

    public static NewGameView create(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, UserDAO userDataAccessObject) {
        // TODO: Update these lines so that it includes the viewmodels that include the views for the games, leaderboard, etc.
        NewGameController newGameController = creatUserNewGameCase(viewManagerModel, newGameViewModel, userDataAccessObject);
        return new NewGameView(newGameViewModel, newGameController);
    }

    private static NewGameController creatUserNewGameCase(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, NewGameDataAccessInterface newGameDataAccessInterface) {
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, viewManagerModel);
        NewGameInputBoundary newGameInteractor = new NewGameInteractor(newGameDataAccessInterface, newGamePresenter);
        return new NewGameController(newGameInteractor);
    }
}
