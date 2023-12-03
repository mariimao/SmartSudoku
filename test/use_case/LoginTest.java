package use_case;

import data_access.UserDAO;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.login.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginTest {
    LoginUserDataAccessInterface userDataBase;

    @Test
    public void testSuccessLogin() {

        LoginInputData inputData = new LoginInputData("name", "pass");
        LoginUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        UserFactory userFactory = new CommonUserFactory();
        Map<LocalTime, Integer> scores_test = new HashMap<>();
        User user = userFactory.create("name", "pass", scores_test);
        userDataBase.addUser(user);

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                assertTrue(userDataBase.existsByName("name"));
                assertEquals("name", loginOutputData.getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failed but is unexpected.");
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, loginPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testUserDNE() {
        LoginInputData inputData = new LoginInputData("1234567890", "pass");
        LoginUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case success but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertFalse(userDataBase.existsByName("1234567890"));
                assertEquals("1234567890: Account does not exist.", error);
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        LoginInputBoundary interactor = new LoginInteractor(userDataBase, loginPresenter);
        interactor.execute(inputData);

    }

    @Test
    public void testPasswordIncorrect() {

        LoginInputData inputData = new LoginInputData("name", "wrong");
        LoginUserDataAccessInterface userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDataBase = userDataAccessObject;

        UserFactory userFactory = new CommonUserFactory();
        Map<LocalTime, Integer> scores_test = new HashMap<>();
        User user = userFactory.create("name", "pass", scores_test);
        userDataBase.addUser(user);

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case success but is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                // user name exists but username is wrong
                assertTrue(userDataBase.existsByName("name"));
                assertEquals("Incorrect password. Try again.", error);
            }
        };

        Map<LocalTime, Integer> scores = new HashMap<>();
        scores.put(LocalTime.now(), 100);
        LoginInputBoundary interactor = new LoginInteractor(userDataBase, loginPresenter);
        interactor.execute(inputData);

    }
}
