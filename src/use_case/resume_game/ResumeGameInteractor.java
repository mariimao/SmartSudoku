package use_case.resume_game;

import entity.user.User;

public class ResumeGameInteractor implements ResumeGameInputBoundary{
    final ResumeGameDataAccessInterface resumeGameDataAccessInterface;
    final ResumeGameOutputBoundary resumeGamePresenter;

    public ResumeGameInteractor(ResumeGameDataAccessInterface resumeGameDataAccessInterface, ResumeGameOutputBoundary resumeGamePresenter) {
        this.resumeGameDataAccessInterface = resumeGameDataAccessInterface;
        this.resumeGamePresenter = resumeGamePresenter;
    }

    @Override
    public void execute(ResumeGameInputData resumeGameInputData) {
        User user = resumeGameDataAccessInterface.get(resumeGameInputData.getUsername());
        if (user == null) {
            System.out.println("null user");
            resumeGamePresenter.prepareFailView("Error: No User is Logged In.");
        }
        else {
            boolean useCaseSuccess = resumeGameDataAccessInterface.getProgress(resumeGameInputData.getUsername())!= null;
            ResumeGameOutputData resumeGameOutputData = new ResumeGameOutputData(user, !useCaseSuccess);
            if (useCaseSuccess) {
                resumeGamePresenter.prepareSuccessView(resumeGameOutputData);
            } else {
                System.out.println("error2");
                resumeGamePresenter.prepareFailView("Error: Could not find a game to resume");
            }
        }
    }
}
