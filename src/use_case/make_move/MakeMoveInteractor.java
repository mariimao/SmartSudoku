package use_case.make_move;

import entity.board.Board;
import entity.board.GameState;
import entity.board.HardBoard;
import interface_adapter.end_game.EndGamePresenter;
import use_case.end_game.EndGameOutputData;
import use_case.user_move.UserMoveBoardDataAccessInterface;
import use_case.user_move.UserMoveOutputData;

import java.util.LinkedList;

public class MakeMoveInteractor implements MakeMoveInputBoundary {
    final MakeMoveDataAccessInterface makeMoveDataAccessInterface;
    final MakeMoveOutputBoundary makeMovePresenter;
    final UserMoveBoardDataAccessInterface userMoveBoardDataAccessInterface;
    public MakeMoveInteractor(MakeMoveDataAccessInterface makeMoveDataAccessInterface, MakeMoveOutputBoundary makeMovePresenter, UserMoveBoardDataAccessInterface userMoveBoardDataAccessInterface) {
        this.makeMoveDataAccessInterface = makeMoveDataAccessInterface;
        this.makeMovePresenter = makeMovePresenter;
        this.userMoveBoardDataAccessInterface = userMoveBoardDataAccessInterface;
    }

    @Override
    public void execute(MakeMoveInputData makeMoveInputData) {
        GameState gameBeingPlayed = makeMoveInputData.getGameBeingPlayed();
        Board boardBeingPlayed = gameBeingPlayed.getCurrBoard();
        String userName = makeMoveInputData.getUserName();
        int x = makeMoveInputData.getMoveCol();
        int y = makeMoveInputData.getMoveRow();
        int val = makeMoveInputData.getMoveValue();

        // Akunna: I followed what was writen in UserMoveInteractor (Anne and Jenn wrote it)

        // if the user's guess is incorrect, remove a life and let them try again only if their lives aren't 0
        if (!boardBeingPlayed.correctMove(x, y, val)) {
            gameBeingPlayed.loseLife();
            if (gameBeingPlayed.getLives() < 1) {
                makeMovePresenter.prepareEndLostView(new MakeMoveOutputData(gameBeingPlayed));
            }
            else{
                makeMovePresenter.prepareWrongMoveView(new MakeMoveOutputData(gameBeingPlayed));
            }
        }

        // if the user's guess is correct, stop the game if it is finished, otherwise it should keep going
        else {
            // creating a new game state with the updated information
            Board boardWithMove = boardBeingPlayed.makeMove(x,y,val);
            LinkedList<GameState> newPastStates = gameBeingPlayed.getPastStates();
            newPastStates.add(gameBeingPlayed);
            GameState gameWithMove = new GameState(gameBeingPlayed.getDifficulty(), newPastStates);
            gameWithMove.setLives(gameBeingPlayed.getLives());

            // sending to endGame if the game is done
            if (boardWithMove.noSpacesLeft()) {
                gameWithMove.setCurrBoard(boardWithMove);
                makeMovePresenter.prepareEndLostView(new MakeMoveOutputData(gameWithMove));
            }

            else {
                // if the difficulty is easy, use our written code to generate a new scrambled board
                if (gameBeingPlayed.getDifficulty() == 1) {
                    gameWithMove.setCurrBoard(boardWithMove);
                    Board scrambledBoard = gameWithMove.scrambleBoard();
                    gameWithMove.setCurrBoard(scrambledBoard);
                    makeMovePresenter.prepareSuccessView(new MakeMoveOutputData(gameWithMove));
                }

                else {
                    // if the difficulty is hard, make a call to the relevant apis to generate a new scrambled board
                    int correct_moves = gameBeingPlayed.getPastStates().size();
                    int[][] scrambledBoardAsArray = userMoveBoardDataAccessInterface.generateBoard(correct_moves);
                    String scrambledBoardAsString = userMoveBoardDataAccessInterface.arrayToString(scrambledBoardAsArray);
                    gameWithMove.setCurrBoard(scrambledBoardAsString);
                    makeMovePresenter.prepareSuccessView(new MakeMoveOutputData(gameWithMove));
                }
            }
        }





//        if (gameBeingPlayed == null) {makeMovePresenter.prepareFailView("Error, Make Move Clicked While No Game Is Being Played");}
//        else{
//            gameBeingPlayed.setCurrBoard(gameBeingPlayed.makeMove(x, y, val));
//            Board scrambledBoard = gameBeingPlayed.scrambleBoard();
//            if (scrambledBoard == null) {makeMovePresenter.prepareFailView("Invalid Input: Your Move is Incorrect");}
//            else {
//                gameBeingPlayed.setCurrBoard(scrambledBoard);  // TODO: scramble Board needs to return null if there is no valid permutation
//                MakeMoveOutputData makeMoveOutputData = new MakeMoveOutputData(gameBeingPlayed);
//                makeMovePresenter.prepareSuccessView(makeMoveOutputData);
//            }
//        }
    }
}

