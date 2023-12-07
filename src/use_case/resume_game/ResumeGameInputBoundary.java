package use_case.resume_game;

import java.io.IOException;

public interface ResumeGameInputBoundary {
    void execute(ResumeGameInputData resumeGameInputData) throws IOException;
}
