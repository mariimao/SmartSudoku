package use_case.make_move;

import entity.board.GameState;

/**
 * This class is the interface of the MakeMove output boundary. It is implemented by MakeMovePresenter.
 */
public interface MakeMoveOutputBoundary {

    /**
     * Called when MakeMove runs successfully - prepares a success view.
     * @param makeMoveOutputData is a MakeMoveOutputData object
     * @return a GameState of the updated game.
     */
    GameState prepareSuccessView(MakeMoveOutputData makeMoveOutputData);

    /**
     * Called when Leaderboard doesn't run successfully - prepares a fail view.
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
