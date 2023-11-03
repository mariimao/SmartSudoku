package interface_adapter.start;

import use_case.start.StartInputBoundary;
import use_case.start.StartInteractor;

public class StartController {

    final StartInputBoundary startUseCaseInteractor;

    public StartController(StartInteractor startUseCaseInteractor) {
        this.startUseCaseInteractor = startUseCaseInteractor;
    }

    public void execute () {
        startUseCaseInteractor.execute();
    }


}
