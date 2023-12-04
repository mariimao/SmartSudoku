package use_case.get_time;

/**
 * Class representing the interactor for the GetTime usecase. This class implements the GetTimeInputBoundary.
 */
public class GetTimeInteractor implements GetTimeInputBoundary {

    final GetTimeOutputBoundary getTimePresenter;

    /**
     * Constructor for the GetTimeInteractor object.
     * @param getTimePresenter is a GetTimeOutputBoundary object
     */
    public GetTimeInteractor(GetTimeOutputBoundary getTimePresenter) {
        this.getTimePresenter = getTimePresenter;
    }

    /**
     * Hypothetically executes the GetTime use case.
     * This function has not been implemented yet, as the GetTime use case is not in use. However, in the future if
     * we wanted to add more
     */
    @Override
    public void execute() {

    }
}
