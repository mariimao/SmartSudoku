package use_case.make_move;

import entity.board.Board;
import entity.board.GameState;
import entity.user.User;

public class MakeMoveInteractor implements MakeMoveInputBoundary {
    final MakeMoveDataAccessInterface makeMoveDataAccessInterface;
    final MakeMoveOutputBoundary makeMovePresenter;

    public MakeMoveInteractor(MakeMoveDataAccessInterface makeMoveDataAccessInterface, MakeMoveOutputBoundary makeMovePresenter) {
        this.makeMoveDataAccessInterface = makeMoveDataAccessInterface;
        this.makeMovePresenter = makeMovePresenter;
    }

    @Override
    public void execute(MakeMoveInputData makeMoveInputData) {
        GameState gameBeingPlayed = makeMoveInputData.getGameBeingPlayed();
        int x = makeMoveInputData.getMoveCol();
        int y = makeMoveInputData.getMoveRow();
        int val = makeMoveInputData.getMoveValue();

        if (gameBeingPlayed == null) {makeMovePresenter.prepareFailView("Error, Make Move Clicked While No Game Is Being Played");}
        else{
            gameBeingPlayed.setCurrBoard(gameBeingPlayed.makeMove(x, y, val));
            Board scrambledBoard = gameBeingPlayed.scrambleBoard();
            if (scrambledBoard == null) {makeMovePresenter.prepareFailView("Invalid Input: Your Move is Incorrect");}
            else {
                gameBeingPlayed.setCurrBoard(scrambledBoard);  // TODO: scramble Board needs to return null if there is no valid permutation
                MakeMoveOutputData makeMoveOutputData = new MakeMoveOutputData(gameBeingPlayed);
                makeMovePresenter.prepareSuccessView(makeMoveOutputData);
            }
        }
    }
}

