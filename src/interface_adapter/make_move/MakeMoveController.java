package interface_adapter.make_move;

import entity.board.GameState;
import use_case.make_move.MakeMoveInputBoundary;
import use_case.make_move.MakeMoveInputData;

public class MakeMoveController {
    final MakeMoveInputBoundary makeMoveInteractor;
    public MakeMoveController(MakeMoveInputBoundary makeMoveInteractor) {
        this.makeMoveInteractor = makeMoveInteractor;
    }

    public void execute(int value, int x, int y, GameState gameBeingPlayed, String userName) { // x is the column, y is the row
        MakeMoveInputData makeMoveInputData = new MakeMoveInputData(userName, value,y, x, gameBeingPlayed);
        makeMoveInteractor.execute(makeMoveInputData);
    }
}
