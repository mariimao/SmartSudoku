package use_case;

import app.Main;
import app.SignupUseCaseFactory;
import app.StartUseCaseFactory;
import data_access.UserDAO;
import entity.board.EasyBoard;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.*;
import view.SignupView;
import view.StartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTest {
    SignupUserDataAccessInterface userDataBase;

    @Test
    public void testSuccessCreation() {
        SignupInputData inputData = new SignupInputData("name", "pass", "pass");
        SignupUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;
        userDataBase.delete("name");

        SignupOutputBoundary creationPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                assertEquals("name", signupOutputData.getUsername());
                assertTrue(userDataBase.existsByName("name"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        SignupInputBoundary interactor = new SignupInteractor(userDataBase, creationPresenter, new CommonUserFactory(), scores);
        interactor.execute(inputData);
    }

    @Test
    public void testIncorrectPassword() {
        SignupInputData inputData = new SignupInputData("name", "pass", "wrong");
        SignupUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        SignupOutputBoundary creationPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail("Use case succeeded but is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists. Please pick a different username.", error);
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        SignupInputBoundary interactor = new SignupInteractor(userDataBase, creationPresenter, new CommonUserFactory(), scores);
        interactor.execute(inputData);

    }

    @Test
    public void testExistingUser() {
        SignupInputData inputData = new SignupInputData("username", "pass", "pass");
        SignupUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        UserFactory userFactory = new CommonUserFactory();
        Map<LocalTime, Integer> scores_test = new HashMap<>();
        User user = userFactory.create("username", "pass1", scores_test);
        userDataBase.addUser(user);

        SignupOutputBoundary creationPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail("Use case success but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists. Please pick a different username.", error);
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        SignupInputBoundary interactor = new SignupInteractor(userDataBase, creationPresenter, new CommonUserFactory(), scores);
        interactor.execute(inputData);
    }
}
