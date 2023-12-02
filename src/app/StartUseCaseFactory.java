package app;

import data_access.UserDAO;
import entity.user.CommonUserFactory;
import entity.user.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartController;
import interface_adapter.start.StartPresenter;
import interface_adapter.start.StartViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupOutputBoundary;
import use_case.start.*;
import view.StartView;

import javax.swing.*;
import java.io.IOException;

/**
 * Use case factory for the StartUseCaseFactory state.
 * This class creates the data for the views in a StartView object.
 */
public class StartUseCaseFactory {

    /** Prevent instantiation. */
    private StartUseCaseFactory() {}

    /**
     * Creates a new StartView object. If the object could not be created, an error will display.
     * @param viewManagerModel is a ViewManagerModel object
     * @param startViewModel is a StartViewModel object
     * @param signupViewModel is a SignupViewModel object
     * @param loginViewModel is a LoginViewModel object
     * @param userDataAccessObject is a UserDataAccessObject
     * @return StartView object, with parameters for newly created relevant models and controllers
     */
    public static StartView create(
            ViewManagerModel viewManagerModel, StartViewModel startViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, StartUserDataAccessInterface userDataAccessObject) {

        try {
            StartController startController = createUserStartUseCase(viewManagerModel, startViewModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new StartView(startController, startViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open page.");
        }

        return null;
    }

    private static StartController createUserStartUseCase(
            ViewManagerModel viewManagerModel, StartViewModel startViewModel,
            SignupViewModel signupViewModel, LoginViewModel loginViewModel,
            StartUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        StartOutputBoundary startOutputBoundary = new StartPresenter(startViewModel, signupViewModel,
                loginViewModel, viewManagerModel);

        StartInputBoundary startInteractor = new StartInteractor(
                userDataAccessObject, startOutputBoundary);

        return new StartController(startInteractor);
    }

}
