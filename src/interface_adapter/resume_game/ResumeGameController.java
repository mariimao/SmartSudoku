package interface_adapter.resume_game;

import entity.board.GameState;
import entity.user.User;
import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInputData;

import java.io.IOException;
import java.util.LinkedList;

public class ResumeGameController {
    final ResumeGameInputBoundary userResumeGameInteractor;

    public ResumeGameController(ResumeGameInputBoundary resumeGameInteractor) {
        this.userResumeGameInteractor = resumeGameInteractor;
    }
    public void execute(String userName) throws IOException {
        // ASSUMPTION: if the User.getProgress() has been called for the user then their paused game and past games have been set into the user attributes
        ResumeGameInputData resumeGameInputData = new ResumeGameInputData(userName);
        userResumeGameInteractor.execute(resumeGameInputData);
    }

}
