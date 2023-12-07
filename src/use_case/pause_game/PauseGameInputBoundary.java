package use_case.pause_game;

import java.io.IOException;

/**
 * Class representing the InputBoundary for the PauseGame usecase. This class is implemented by the PauseGameInteractor.
 */
public interface PauseGameInputBoundary {

    /**
     * Executes the PauseGame use case.
     * @param pauseGameInputData is an PauseGameInputData object
     */
    void execute(PauseGameInputData pauseGameInputData) throws IOException;
}
