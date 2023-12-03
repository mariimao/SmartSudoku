package use_case.make_move;

import use_case.end_game.EndGameOutputData;

public interface MakeMoveOutputBoundary {
    void prepareSuccessView(MakeMoveOutputData makeMoveOutputData);
    void prepareFailView(String error);
    void prepareWrongMoveView(MakeMoveOutputData makeMoveOutputData);

    void prepareEndLostView(MakeMoveOutputData makeMoveOutputData);

    void prepareEndWonView(MakeMoveOutputData makeMoveOutputData);
}
