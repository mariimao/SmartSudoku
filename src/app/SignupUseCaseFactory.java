package app;

import data_access.UserDAO;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.signup.cancel.CancelPresenter;
import interface_adapter.signup.cancel.CancelViewModel;
import interface_adapter.start.StartViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.signup.cancel.CancelInputBoundary;
import use_case.signup.cancel.CancelInteractor;
import use_case.signup.cancel.CancelOutputBoundary;
import use_case.signup.cancel.CancelUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            CancelViewModel cancelViewModel, StartViewModel startViewModel,
            UserDAO userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            CancelController cancelController = createUserClearUseCase(viewManagerModel, cancelViewModel, startViewModel);
            return new SignupView(signupController, signupViewModel, cancelController);
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

    private static CancelController createUserClearUseCase(ViewManagerModel viewManagerModel, 
                                                           CancelViewModel cancelViewModel, StartViewModel startViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CancelOutputBoundary clearOutputBoundary = new CancelPresenter(cancelViewModel, startViewModel, viewManagerModel);

        CancelInputBoundary cancelInteractor = new CancelInteractor(
                clearOutputBoundary);

        return new CancelController(cancelInteractor);
    }

}


