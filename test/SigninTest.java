import data_access.UserDAO;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.Test;
import use_case.signup.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SigninTest {

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
    public void testIncorrectTest() {
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
                assertEquals("Passwords do not match. Try again.", error);
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
