package use_case.start;

import data_access.UserDAO;

public class StartInteractor implements StartInputBoundary {

    final StartUserDataAccessInterface startUserDataAccessInterface;
    final StartOutputBoundary startPresenter;

    public StartInteractor(StartUserDataAccessInterface startUserDataAccessInterface,
                           StartOutputBoundary startPresenter) {
        this.startUserDataAccessInterface = startUserDataAccessInterface;
        this.startPresenter = startPresenter;
    }

    @Override
    public void execute() {
        // redirects the user to the menu
        //StartOutputData startOutputData = new StartOutputData();
        startPresenter.prepareSuccessView();
    }
}
