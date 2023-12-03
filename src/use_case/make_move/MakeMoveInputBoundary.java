package use_case.make_move;

import entity.board.GameState;

public interface MakeMoveInputBoundary {
    GameState execute(MakeMoveInputData makeMoveInputData);
}
