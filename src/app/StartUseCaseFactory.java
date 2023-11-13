package app;

import data_access.UserDAO;
import entity.CommonUserFactory;
import entity.UserFactory;
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

public class StartUseCaseFactory {

    private StartUseCaseFactory() {}

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
