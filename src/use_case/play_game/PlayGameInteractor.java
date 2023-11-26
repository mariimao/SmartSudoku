package use_case.play_game;

import entity.board.GameState;
import entity.user.User;

public class PlayGameInteractor implements PlayGameInputBoundary {
    final PlayGameDataAccessInterface newGameDataAccessInterface;
    final PlayGameOutputBoundary newGamePresenter;

    public PlayGameInteractor(PlayGameDataAccessInterface newGameDataAccessInterface, PlayGameOutputBoundary newGamePresenter) {
        this.newGameDataAccessInterface = newGameDataAccessInterface;
        this.newGamePresenter = newGamePresenter;
    }

    @Override
    public void execute(PlayGameInputData playGameInputData) {

    }
}

