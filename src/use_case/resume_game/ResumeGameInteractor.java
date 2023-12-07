package use_case.resume_game;

import entity.SpotifyPlayer;
import entity.user.User;
import use_case.play_music.PlayMusicDataAccessInterface;

import java.io.IOException;

/**
 * Class of Presenter for resume game use case. Implements ResumeGameInputBoundary
 */
public class ResumeGameInteractor implements ResumeGameInputBoundary{
    final ResumeGameDataAccessInterface resumeGameDataAccessInterface;

    final PlayMusicDataAccessInterface playMusicDataAccessInterface;

    final ResumeGameOutputBoundary resumeGamePresenter;

    /**
     * Constructor for MakeMovePresenter
     * @param resumeGameDataAccessInterface the interface for data access of users
     * @param playMusicDataAccessInterface  the interface for data access of spotify data
     * @param resumeGamePresenter the presenter, is a ResumeGameOutputBoundary type
     */
    public ResumeGameInteractor(ResumeGameDataAccessInterface resumeGameDataAccessInterface, PlayMusicDataAccessInterface playMusicDataAccessInterface, ResumeGameOutputBoundary resumeGamePresenter) {
        this.resumeGameDataAccessInterface = resumeGameDataAccessInterface;
        this.playMusicDataAccessInterface = playMusicDataAccessInterface;
        this.resumeGamePresenter = resumeGamePresenter;
    }

    /**
     * Execute the use case
     * @param resumeGameInputData   the resume game data
     * @throws IOException             throws exception is there is an error
     */
    @Override
    public void execute(ResumeGameInputData resumeGameInputData) throws IOException {
        User user = resumeGameDataAccessInterface.get(resumeGameInputData.getUsername());
        if (user == null) {
            resumeGamePresenter.prepareFailView("Error: No User is Logged In.");
        }
        else {
            boolean useCaseSuccess = resumeGameDataAccessInterface.getProgress(resumeGameInputData.getUsername())!= null;
            ResumeGameOutputData resumeGameOutputData = new ResumeGameOutputData(user, !useCaseSuccess);
            if (useCaseSuccess) {

                //unpause music as well
                String token = playMusicDataAccessInterface.getRefreshToken();
                SpotifyPlayer spotifyPlayer = new SpotifyPlayer(token);
                if (!spotifyPlayer.getDevices().isEmpty()) {
                    String device = spotifyPlayer.getCurrentDevice();
                    spotifyPlayer.pause(device);
                }

                resumeGamePresenter.prepareSuccessView(resumeGameOutputData);
            } else {
                System.out.println("error2");
                resumeGamePresenter.prepareFailView("Error: Could not find a game to resume");
            }
        }
    }
}
