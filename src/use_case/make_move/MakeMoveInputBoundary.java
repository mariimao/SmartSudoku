package use_case.make_move;

import entity.board.GameState;

import java.io.IOException;

public interface MakeMoveInputBoundary {
    GameState execute(MakeMoveInputData makeMoveInputData) throws IOException;
}
