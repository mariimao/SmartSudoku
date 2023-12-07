package use_case.make_move;

import entity.board.Board;
import entity.board.GameState;

import java.io.IOException;
import java.util.Arrays;

/**
 * Class representing the interactor for the MakeMove usecase. This class implements the MakeMoveInputBoundary.
 */
public class MakeMoveInteractor implements MakeMoveInputBoundary {
    final MakeMoveDataAccessInterface makeMoveDataAccessInterface;
    final MakeMoveOutputBoundary makeMovePresenter;
    final MakeMoveBoardDataAccessInterface makeMoveBoardDataAccessInterface;

    /**
     * Constructor for the MakeMove interactor.
     * @param makeMoveDataAccessInterface is a MakeMoveDataAccessInterface object
     * @param makeMovePresenter is a MakeMoveOutputBoundary object
     * @param makeMoveBoardDataAccessInterface is a MakeMoveBoardDataAccessInterface object
     */
    public MakeMoveInteractor(MakeMoveDataAccessInterface makeMoveDataAccessInterface, MakeMoveOutputBoundary makeMovePresenter, MakeMoveBoardDataAccessInterface makeMoveBoardDataAccessInterface) {
        this.makeMoveDataAccessInterface = makeMoveDataAccessInterface;
        this.makeMovePresenter = makeMovePresenter;
        this.makeMoveBoardDataAccessInterface = makeMoveBoardDataAccessInterface;
    }

    /**
     * Executes the MakeMode use case.
     * If the move is correct, the board will scramble, then update. If the move is incorrect, the user will lose a life.
     * @param makeMoveInputData is an MakeMoveInputData object
     * @return a GameState object representing the updated state of the game.
     */
    @Override
    public GameState execute(MakeMoveInputData makeMoveInputData) throws IOException {
        GameState gameBeingPlayed = makeMoveInputData.getGameBeingPlayed();
        int x = makeMoveInputData.getMoveCol();
        int y = makeMoveInputData.getMoveRow();
        int val = makeMoveInputData.getMoveValue();
        if (gameBeingPlayed == null) {makeMovePresenter.prepareFailView("Error, Make Move Clicked While No Game Is Being Played");}
        else{
            if (gameBeingPlayed.correctMove(x, y, val)) {
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

