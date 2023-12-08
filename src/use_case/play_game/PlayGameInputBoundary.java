package use_case.play_game;

/**
 * Class representing the InputBoundary for the play game use case. This class is implemented by the PlayGameInteractor.
 */
public interface PlayGameInputBoundary {

    /**
     * Executes the play game use case.
     *
     * @param playGameInputData is a PlayGameInputData object
     */
    void execute(PlayGameInputData playGameInputData);
}
