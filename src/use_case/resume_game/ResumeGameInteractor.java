package use_case.resume_game;

import entity.user.User;

public class ResumeGameInteractor implements ResumeGameInputBoundary{
    final ResumeGameDataAccessInterface resumeGameDataAccessInterface;
    final ResumeGameOutputBoundary resumeGamePresenter;
    final User user;

    public ResumeGameInteractor(ResumeGameDataAccessInterface resumeGameDataAccessInterface, ResumeGameOutputBoundary resumeGamePresenter, User user) {
        this.resumeGameDataAccessInterface = resumeGameDataAccessInterface;
        this.resumeGamePresenter = resumeGamePresenter;
        this.user = user;
    }

    @Override
    public void execute(ResumeGameInputData resumeGameInputData) {
        if (user == null) {resumeGamePresenter.prepareFailView("Error: No User is Logged In.");}
        else {
            boolean useCaseSuccess = this.user.getPausedGame() != null;
            ResumeGameOutputData resumeGameOutputData = new ResumeGameOutputData(user, !useCaseSuccess);
            if (useCaseSuccess) {
                resumeGamePresenter.prepareSuccessView(resumeGameOutputData);
            } else {
                resumeGamePresenter.prepareFailView("Error: Could not find a game to resume");
            }
        }
    }
}
