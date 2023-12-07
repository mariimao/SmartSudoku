package interface_adapter.resume_game;

import use_case.resume_game.ResumeGameInputBoundary;
import use_case.resume_game.ResumeGameInputData;

import java.io.IOException;

/**
 * The class for ResumeGameController. Acts as controller to send information to ResumeGameInteractor.
 */
public class ResumeGameController {
    final ResumeGameInputBoundary userResumeGameInteractor;

    /**
     * Constructor of ResumeGameController
     *
     * @param resumeGameInteractor the interactor that makes decisions with input data
     */
    public ResumeGameController(ResumeGameInputBoundary resumeGameInteractor) {
        this.userResumeGameInteractor = resumeGameInteractor;
    }

    /**
     * Executes the use case's interactor to perform action
     * ASSUMPTION: if the User.getProgress() has been called for the user then
     * their paused game and past games have been set into the user attributes     * @param userName      the name of the user
     *
     * @throws IOException throws exception if can't find user
     */
    public void execute(String userName) throws IOException {
        ResumeGameInputData resumeGameInputData = new ResumeGameInputData(userName);
        userResumeGameInteractor.execute(resumeGameInputData);
    }

}
