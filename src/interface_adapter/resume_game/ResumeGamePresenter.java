package interface_adapter.resume_game;

import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.resume_game.ResumeGameOutputData;

public class ResumeGamePresenter implements ResumeGameOutputBoundary {
    @Override
    public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
