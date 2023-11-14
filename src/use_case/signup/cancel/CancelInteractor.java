package use_case.signup.cancel;


//import use_case.signup.SignupOutputBoundary;
// import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalTime;
import java.util.Map;

public class CancelInteractor implements CancelInputBoundary{
    // final SignupUserDataAccessInterface signupUserDataAccessInterface;
    final CancelOutputBoundary cancelPresenter;

    public CancelInteractor(CancelOutputBoundary cancelPresenter) {
        this.cancelPresenter = cancelPresenter;
    }

    @Override
    public void execute() {
        cancelPresenter.prepareSuccessView();

    }
}
