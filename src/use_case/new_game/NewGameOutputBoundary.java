package use_case.new_game;

/**
 * This class is the interface of the NewGame output boundary. It is implemented by NewGamePresenter.
 */
public interface NewGameOutputBoundary {

    /**
     * Called when NewGame runs successfully - prepares a success view.
     * @param newGameOutputData is an NewGameOutputData object
     */
    void prepareSuccessView(NewGameOutputData newGameOutputData);

    /**
     * Called when NewGame doesn't run successfully - prepares a fail view.
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
