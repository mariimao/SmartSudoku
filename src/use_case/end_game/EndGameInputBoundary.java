package use_case.end_game;

/**
 * Class representing the InputBoundary for the EndGame usecase. This class is implemented by the EndGameInteractor.
 */
public interface EndGameInputBoundary {

    /**
     * Executes the EndGame use case.
     * @param endGameInputData is an EndGameInputData object
     */
    void execute(EndGameInputData endGameInputData);
}
