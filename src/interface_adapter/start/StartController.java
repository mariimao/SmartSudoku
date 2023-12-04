package interface_adapter.start;

import use_case.start.StartInputBoundary;
import use_case.start.StartInputData;
import use_case.start.StartInteractor;

/**
 * The class for StartController. Acts as controller to send information to StartInteractor.
 */
public class StartController {

    final StartInputBoundary startUseCaseInteractor;

    /**
     * Constructor of Start Controller
     * @param startUseCaseInteractor the interactor that makes decisions with input data
     */
    public StartController(StartInputBoundary startUseCaseInteractor) {
        this.startUseCaseInteractor = startUseCaseInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     * @param interaction string that identifies what type of button was pressed
     */
    public void execute (String interaction) {
        StartInputData startInputData = new StartInputData(interaction);
        startUseCaseInteractor.execute(startInputData);
    }


}
