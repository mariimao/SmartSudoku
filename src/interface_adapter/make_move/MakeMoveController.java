package interface_adapter.make_move;

import entity.board.GameState;
import use_case.make_move.MakeMoveInputBoundary;
import use_case.make_move.MakeMoveInputData;

import java.io.IOException;

/**
 * The Controller for MakeMove. Creates input data to be used by the controller.
 */
public class MakeMoveController {
    final MakeMoveInputBoundary makeMoveInteractor;

    /**
     * Constructor of MakeMove Controller
     * @param makeMoveInteractor the interactor that makes decisions with input data
     */
    public MakeMoveController(MakeMoveInputBoundary makeMoveInteractor) {
        this.makeMoveInteractor = makeMoveInteractor;
    }

    /**
     * Executes the use case interactor to perform action
     * @param value             the value the user entered
     * @param x                 the column value of the box they inputted
     * @param y                 the row value of the box they inputted
     * @param gameBeingPlayed   the current game being played, is a GameState object
     * @return                  returns a GameState
     * @throws IOException      exception if the value entered is invalid
     */
    public GameState execute(int value, int x, int y, GameState gameBeingPlayed) throws IOException {
        MakeMoveInputData makeMoveInputData = new MakeMoveInputData(value,x, y, gameBeingPlayed);
        return makeMoveInteractor.execute(makeMoveInputData);
    }
}
