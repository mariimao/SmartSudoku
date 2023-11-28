package interface_adapter.play_game;

import use_case.play_game.PlayGameOutputBoundary;
import use_case.play_game.PlayGameOutputData;

public class PlayGamePresenter implements PlayGameOutputBoundary {
    @Override
    public void prepareSuccessView(PlayGameOutputData newGameOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
