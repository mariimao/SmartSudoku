package use_case.make_move;

import entity.board.GameState;

public interface MakeMoveOutputBoundary {
    GameState prepareSuccessView(MakeMoveOutputData makeMoveOutputData);
    void prepareFailView(String error);
}
