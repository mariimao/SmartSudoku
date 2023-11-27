package interface_adapter.startplayer;


import use_case.startplayer.StartPlayerInputBoundary;
import use_case.startplayer.StartPlayerInputData;

public class StartPlayerController {
    final StartPlayerInputBoundary startPlayerUseCaseInt;

    public StartPlayerController(StartPlayerInputBoundary startPlayerUseCaseInteractor) {
        this.startPlayerUseCaseInt = startPlayerUseCaseInteractor;
    }

    public void execute (String trackID) {
        StartPlayerInputData startPlayerInputData = new StartPlayerInputData(trackID);
        startPlayerUseCaseInt.execute(startPlayerInputData);
    }
}
