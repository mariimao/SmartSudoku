package use_case.signup;


import entity.user.*;

import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface signupUserDataAccessInterface;
    final SignupOutputBoundary signupPresenter;

    final UserFactory userFactory;

    final Map<LocalTime, Integer> scores;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                           SignupOutputBoundary signupPresenter, UserFactory userFactory,
                            Map<LocalTime, Integer> scores) {
        this.signupUserDataAccessInterface = signupUserDataAccessInterface;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
        this.scores = scores;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupUserDataAccessInterface.existsByName(signupInputData.getUsername())){
            signupPresenter.prepareFailView("Username already exists. Please pick a different username.");
        }
        else if (!Objects.equals(signupInputData.getPassword(), signupInputData.getRepeatPassword())) {
            signupPresenter.prepareFailView("Passwords do not match. Try again.");
        }
        else {

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), scores);
            signupUserDataAccessInterface.addUser(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            signupPresenter.prepareSuccessView(signupOutputData);
        }

    }
}
