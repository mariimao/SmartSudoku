package use_case.resume_game;

import entity.SpotifyPlayer;
import entity.user.User;
import use_case.play_music.PlayMusicDataAccessInterface;

import java.io.IOException;

public class ResumeGameInteractor implements ResumeGameInputBoundary{
    final ResumeGameDataAccessInterface resumeGameDataAccessInterface;

    final PlayMusicDataAccessInterface playMusicDataAccessInterface;

    final ResumeGameOutputBoundary resumeGamePresenter;

    public ResumeGameInteractor(ResumeGameDataAccessInterface resumeGameDataAccessInterface, PlayMusicDataAccessInterface playMusicDataAccessInterface, ResumeGameOutputBoundary resumeGamePresenter) {
        this.resumeGameDataAccessInterface = resumeGameDataAccessInterface;
        this.playMusicDataAccessInterface = playMusicDataAccessInterface;
        this.resumeGamePresenter = resumeGamePresenter;
    }

    @Override
    public void execute(ResumeGameInputData resumeGameInputData) throws IOException {
        User user = resumeGameDataAccessInterface.get(resumeGameInputData.getUsername());
        if (user == null) {
            System.out.println("null user");
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
