package use_case.new_game;

/**
 * Class representing the InputBoundary for the NewGame usecase. This class is implemented by the NewGameInteractor.
 */
public interface NewGameInputBoundary {

    /**
     * Executes the NewGame use case.
     *
     * @param newGameInputData is an NewGameInputData object
     */
    void execute(NewGameInputData newGameInputData);
}
