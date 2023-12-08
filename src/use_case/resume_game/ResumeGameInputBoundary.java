package use_case.resume_game;

import java.io.IOException;

/**
 * Class representing the InputBoundary for the resume game usecase. This class is implemented by the ResumeGameInteractor.
 */
public interface ResumeGameInputBoundary {

    /**
     * Executes the resume game use case.
     *
     * @param resumeGameInputData is a ResumeGameInputData object
     */
    void execute(ResumeGameInputData resumeGameInputData) throws IOException;
}
