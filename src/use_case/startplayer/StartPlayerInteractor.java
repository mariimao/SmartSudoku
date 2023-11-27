package use_case.startplayer;

public class StartPlayerInteractor implements StartPlayerInputBoundary{

    private final StartPlayerOutputBoundary startPlayerPresenter;
    private final StartPlayerDataAccessInterface startPlayerUserDataAccessInterface;

    public StartPlayerInteractor(StartPlayerDataAccessInterface startPlayerDataAccessInterface,
                                 StartPlayerOutputBoundary startPresenter) {
        this.startPlayerUserDataAccessInterface = startPlayerDataAccessInterface;
        this.startPlayerPresenter = startPresenter;
    }
    @Override
    public void execute(StartPlayerInputData startPlayerInputData) {
        // check if song id exists
    }
}
