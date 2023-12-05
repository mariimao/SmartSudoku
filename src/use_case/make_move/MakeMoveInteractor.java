package use_case.make_move;

import entity.board.Board;
import entity.board.GameState;
import entity.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MakeMoveInteractor implements MakeMoveInputBoundary {
    final MakeMoveDataAccessInterface makeMoveDataAccessInterface;
    final MakeMoveOutputBoundary makeMovePresenter;
    final MakeMoveBoardDataAccessInterface makeMoveBoardDataAccessInterface;

    public MakeMoveInteractor(MakeMoveDataAccessInterface makeMoveDataAccessInterface, MakeMoveOutputBoundary makeMovePresenter, MakeMoveBoardDataAccessInterface makeMoveBoardDataAccessInterface) {
        this.makeMoveDataAccessInterface = makeMoveDataAccessInterface;
        this.makeMovePresenter = makeMovePresenter;
        this.makeMoveBoardDataAccessInterface = makeMoveBoardDataAccessInterface;
    }

    @Override
    public GameState execute(MakeMoveInputData makeMoveInputData) throws IOException {
        GameState gameBeingPlayed = makeMoveInputData.getGameBeingPlayed();
        int x = makeMoveInputData.getMoveCol();
        int y = makeMoveInputData.getMoveRow();
        int val = makeMoveInputData.getMoveValue();
        if (gameBeingPlayed == null) {makeMovePresenter.prepareFailView("Error, Make Move Clicked While No Game Is Being Played");}
        else{
            System.out.println("hello");
            System.out.println(gameBeingPlayed.getCurrBoard());
            System.out.println("solutions");
            System.out.println(Arrays.deepToString(gameBeingPlayed.getCurrBoard().getSolutionBoard()));

            if (gameBeingPlayed.correctMove(x, y, val)) {
                LinkedList<GameState> newPastStates= gameBeingPlayed.getPastStates();
                newPastStates.add(gameBeingPlayed);
                gameBeingPlayed.setPastStates(newPastStates);
                gameBeingPlayed.setCurrBoard(gameBeingPlayed.makeMove(x, y, val));
                if (makeMoveInputData.getGameBeingPlayed().getDifficulty() == 1) {
                    Board scrambledBoard = gameBeingPlayed.scrambleBoard();
                    gameBeingPlayed.setCurrBoard(scrambledBoard);
                    MakeMoveOutputData makeMoveOutputData = new MakeMoveOutputData(gameBeingPlayed);
                    return makeMovePresenter.prepareSuccessView(makeMoveOutputData);
                } else {
                    int correct_moves = makeMoveInputData.getGameBeingPlayed().getPastStates().size();
                    makeMoveInputData.getGameBeingPlayed().getCurrBoard().setBoard(makeMoveBoardDataAccessInterface.convertToHashMap(makeMoveBoardDataAccessInterface.generateBoard(correct_moves))); // returns int [][]
                    MakeMoveOutputData makeMoveOutputData = new MakeMoveOutputData(gameBeingPlayed);
                    return makeMovePresenter.prepareSuccessView(makeMoveOutputData);
                }
            } else {
                makeMoveInputData.loseLife();
                makeMovePresenter.prepareFailView("Your Move is Incorrect");
            }
        }
        return gameBeingPlayed;
    }
}

