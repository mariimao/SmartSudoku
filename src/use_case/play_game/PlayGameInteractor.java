package use_case.play_game;

import entity.board.GameState;
import entity.user.User;
import use_case.new_game.NewGameOutputData;

public class PlayGameInteractor implements PlayGameInputBoundary {
    final PlayGameDataAccessInterface playGameDataAccessInterface;
    final PlayGameOutputBoundary playGamePresenter;

    public PlayGameInteractor(PlayGameDataAccessInterface playGameDataAccessInterface, PlayGameOutputBoundary playGamePresenter) {
        this.playGameDataAccessInterface = playGameDataAccessInterface;
        this.playGamePresenter = playGamePresenter;
    }

    @Override
    public void execute(PlayGameInputData playGameInputData) {
        // if there is no User logged in, try again maybe take them back to login view
        User user = playGameDataAccessInterface.get(playGameInputData.getUsername());
        if (user == null) {playGamePresenter.prepareFailView("Error: No User is Logged In");}
        else {
            GameState game = playGameInputData.getCurrentGame();
            int difficulty = playGameInputData.getDifficulty();

            // If game is null then a new game has been initiated and a fresh board will be created
            if (game == null) {
                GameState nextGame = new GameState(difficulty);
                PlayGameOutputData playGameOutputData = new PlayGameOutputData(user, nextGame);
                playGamePresenter.prepareSuccessView(playGameOutputData);
            }

            // If game holds an instance of GameState then the board will be scrambled and passed on
            else {
                game.scrambleBoard();  // TODO: remove and check behaviour
                PlayGameOutputData playGameOutputData = new PlayGameOutputData(user, game);
                playGamePresenter.prepareSuccessView(playGameOutputData);
            }
        }
    }
}

