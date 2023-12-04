package interface_adapter.make_move;

import entity.board.GameState;
import use_case.make_move.MakeMoveInputBoundary;
import use_case.make_move.MakeMoveInputData;

import java.io.IOException;

public class MakeMoveController {
    final MakeMoveInputBoundary makeMoveInteractor;
    public MakeMoveController(MakeMoveInputBoundary makeMoveInteractor) {
        this.makeMoveInteractor = makeMoveInteractor;
    }

    public GameState execute(int value, int x, int y, GameState gameBeingPlayed) throws IOException { // x is the column, y is the row
        MakeMoveInputData makeMoveInputData = new MakeMoveInputData(value,x, y, gameBeingPlayed);
        return makeMoveInteractor.execute(makeMoveInputData);
    }
}
