import app.MenuUseCaseFactory;
import data_access.SpotifyDAO;
import data_access.UserDAO;
import entity.user.CommonUserFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MenuTest {

    private Component[] menuComponents;

    @Before
    public void init() throws Exception {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        SpotifyDAO spotifyDAO = new SpotifyDAO();
        JPanel menuView = MenuUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getMenuViewModel(), useCaseTestObjects.getResumeGameViewModel(),
                useCaseTestObjects.getLoginViewModel(), useCaseTestObjects.getNewGameViewModel(),
                userDAO, useCaseTestObjects.getLeaderboardViewModel(), useCaseTestObjects.getPlayGameViewModel(), spotifyDAO);

        assert menuView != null;
        menuComponents = menuView.getComponents();
    }

    @Test
    public void testLoadButtonPresent() {
        JButton button = (JButton) menuComponents[1];
        assert (button.getText().equals("LOAD GAME"));
    }

    @Test
    public void testNewGameButtonPresent() {
        JButton button = (JButton) menuComponents[2];
        assert (button.getText().equals("NEW GAME"));
    }

    @Test
    public void testLeaderboardButtonPresent() {
        JButton button = (JButton) menuComponents[3];
        assert (button.getText().equals("LEADERBOARD"));
    }

    @Test
    public void testBackButtonPresent() {
        JButton button = (JButton) menuComponents[4];
        assert (button.getText().equals("BACK"));
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
