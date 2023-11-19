package use_case.resume_game;

public interface ResumeGameOutputBoundary {
    void prepareSuccessView(ResumeGameOutputData resumeGameOutputData);
    void prepareFailView(String error);
}
