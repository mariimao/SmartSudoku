package use_case.make_move;

import entity.board.GameState;

import java.io.IOException;

/**
 * Class representing the InputBoundary for the MakeMove usecase. This class is implemented by the MakeMoveInteractor.
 */
public interface MakeMoveInputBoundary {

    /**
     * Executes the MakeMove use case.
     *
     * @param makeMoveInputData is an MakeMoveInputData object
     * @return a GameState object
     */
    GameState execute(MakeMoveInputData makeMoveInputData) throws IOException;
}
