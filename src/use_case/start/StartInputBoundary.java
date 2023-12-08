package use_case.start;

/**
 * Class representing the InputBoundary for the start usecase. This class is implemented by the StartInteractor.
 */
public interface StartInputBoundary {

    /**
     * Executes the star use case.
     *
     * @param startInputData is a StartInputData object
     */

    void execute(StartInputData startInputData);
}
