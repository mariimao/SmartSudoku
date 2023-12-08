package use_case.start;

/**
 * Class representing the interactor for the start usecase. This class implements the StartInputBoundary.
 */

public class StartInteractor implements StartInputBoundary {

    final StartUserDataAccessInterface startUserDataAccessInterface;
    final StartOutputBoundary startPresenter;

    /**
     * Constructor for the StartInteractor object.
     *
     * @param startUserDataAccessInterface is a StartUserDataAccessInterface object
     * @param startPresenter           is a StartOutputBoundary object
     */


    public StartInteractor(StartUserDataAccessInterface startUserDataAccessInterface,
                           StartOutputBoundary startPresenter) {
        this.startUserDataAccessInterface = startUserDataAccessInterface;
        this.startPresenter = startPresenter;
    }

    /**
     * This function executes the Start UseCase.
     *
     * @param startInputData is an StartInputData object
     */

    @Override
    public void execute(StartInputData startInputData) {
        StartOutputData startOutputData = new StartOutputData(startInputData.getInteracton());
        startPresenter.prepareSuccessView(startOutputData);
    }
}
