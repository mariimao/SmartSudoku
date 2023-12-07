package use_case.pause_game;

import entity.SpotifyPlayer;
import entity.user.User;
import use_case.play_music.PlayMusicDataAccessInterface;

import java.io.IOException;

/**
 * Class representing the interactor for the PauseGame usecase. This class implements the PauseGameInputBoundary.
 */
public class PauseGameInteractor implements PauseGameInputBoundary {
    final PauseGameDataAccessInterface pauseGameDataAccessInterface;
    final PlayMusicDataAccessInterface playMusicDataAccessInterface;
    final PauseGameOutputBoundary pauseGamePresenter;

    /**
     * Is a constructor for a PauseGameInteractor object.
     *
     * @param pauseGameDataAccessInterface is a PauseGameDataAccessInterface object
     * @param pauseGamePresenter           is a PauseGameOutputBoundary object
     */
    public PauseGameInteractor(PauseGameDataAccessInterface pauseGameDataAccessInterface, PlayMusicDataAccessInterface playMusicDataAccessInterface, PauseGameOutputBoundary pauseGamePresenter) {
        this.pauseGameDataAccessInterface = pauseGameDataAccessInterface;
        this.playMusicDataAccessInterface = playMusicDataAccessInterface;
        this.pauseGamePresenter = pauseGamePresenter;
    }

    /**
     * Executes the PauseGame use case.
     * This method saves the user's current game so it can be loaded later. If this succeeds, it prepares the success
     * view. If not, then it calls the fail view.
     *
     * @param pauseGameInputData is an PauseGameInputData object
     */
    @Override
    public void execute(PauseGameInputData pauseGameInputData) throws IOException {
        User user = pauseGameDataAccessInterface.get(pauseGameInputData.getUsername());
        user.setPausedGame(pauseGameInputData.getCurrent_state());
        boolean useCaseSuccess = pauseGameDataAccessInterface.setProgress(user);  // try and save the User's current game
        PauseGameOutputData pauseGameOutputData = new PauseGameOutputData(user, useCaseSuccess);  // create OutputData

        //pausing music as well
        String token = playMusicDataAccessInterface.getRefreshToken();
        SpotifyPlayer spotifyPlayer = new SpotifyPlayer(token);
        if (!spotifyPlayer.getDevices().isEmpty()) {
            String device = spotifyPlayer.getCurrentDevice();
            spotifyPlayer.pause(device);
        }

        if (useCaseSuccess) {
            pauseGamePresenter.prepareSuccessView(pauseGameOutputData);
        }  // if it was saved show success view
        else {
            pauseGamePresenter.prepareFailView("Data was not saved");
        }  // if not, show failed view

    }
}
