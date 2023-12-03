package use_case;

import app.Main;
import app.MenuUseCaseFactory;
import app.SignupUseCaseFactory;
import app.StartUseCaseFactory;
import data_access.UserDAO;
import entity.user.CommonUserFactory;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.signup.*;
import view.MenuView;
import view.SignupView;
import view.StartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    private Component[] menuComponents;

    @Before
    public void init() throws Exception {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        JPanel menuView = MenuUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getMenuViewModel(), useCaseTestObjects.getResumeGameViewModel(),
                useCaseTestObjects.getLoginViewModel(), useCaseTestObjects.getNewGameViewModel(),
                userDAO, useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getPlayGameViewModel());

        assert menuView != null;
        menuComponents = menuView.getComponents();
    }

    @Test
    public void testLoadButtonPresent() {
        JButton button = (JButton) menuComponents[1];
        assert(button.getText().equals("LOAD GAME"));
    }

    @Test
    public void testNewGameButtonPresent() {
        JButton button = (JButton) menuComponents[2];
        assert(button.getText().equals("NEW GAME"));
    }

    @Test
    public void testLeaderboardButtonPresent() {
        JButton button = (JButton) menuComponents[3];
        assert(button.getText().equals("LEADERBOARD"));
    }

    @Test
    public void testPastGamesPresent() {
        JButton button = (JButton) menuComponents[4];
        assert(button.getText().equals("PAST GAMES"));
    }

    @Test
    public void testBackButtonPresent() {
        JButton button = (JButton) menuComponents[5];
        assert(button.getText().equals("BACK"));
    }

    @Test
    public void testInteractorExecutes() {
        MenuUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MenuUserDataAccessInterface userDataBase = userDataAccessObject;
        MenuOutputBoundary creationPresenter = new MenuOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        MenuInputBoundary interactor = new MenuInteractor(userDataBase, creationPresenter);
        interactor.execute();
    }

}
