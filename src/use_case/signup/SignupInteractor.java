package use_case.signup;


import entity.user.*;

import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing the interactor for the Signup usecase. This class implements the SignupInputBoundary.
 */
public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface signupUserDataAccessInterface;
    final SignupOutputBoundary signupPresenter;

    final UserFactory userFactory;

    final Map<LocalTime, Integer> scores;

    /**
     * Constructor for the SignupInteractor object.
     * @param signupUserDataAccessInterface is a SignupUserDataAccessInterface object
     * @param signupPresenter is a SignupOutputBoundary object
     * @param userFactory is the userFactory that creates the users
     * @param scores is a mapping of the past scores
     */
    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                           SignupOutputBoundary signupPresenter, UserFactory userFactory,
                            Map<LocalTime, Integer> scores) {
        this.signupUserDataAccessInterface = signupUserDataAccessInterface;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
        this.scores = scores;
    }

    /**
     * Executes the Signup UseCase.
     * This function creates a new User object and stores it into the MongoDB database if all inputs are valid.
     * If any input are invalid such as already existing username and non-matching passwords, it will send a fail view
     * to the presenter.
     * @param signupInputData is an SignupInputData object
     */
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
