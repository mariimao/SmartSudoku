package app;

import data_access.UserDAO;
import entity.user.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Use case factory for the SignUpUseCaseFactory state.
 * This class creates the data for the views in a SignupView object.
 */
public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    /**
     * Creates a new SignupView object. If the data file could not be read, a JDialogue showing the error
     * will display.
     * @param viewManagerModel is a ViewManagerModel object
     * @param loginViewModel is a LoginViewModel object
     * @param signupViewModel is a SignupViewModel object
     * @param userDataAccessObject is a UserDataAccessObject
     * @return SignupView object, with parameters for newly created relevant models and controllers
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            UserDAO userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, 
                                                            LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {
        
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupViewModel, loginViewModel, viewManagerModel);

        UserFactory userFactory = new CommonUserFactory();

        Map<LocalTime, Integer> scores = new HashMap<>();
        
        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory, scores);

        return new SignupController(userSignupInteractor);
    }

}


