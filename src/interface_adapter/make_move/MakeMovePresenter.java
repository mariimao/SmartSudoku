package interface_adapter.make_move;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import use_case.end_game.EndGameOutputData;
import use_case.make_move.MakeMoveInputBoundary;
import use_case.make_move.MakeMoveOutputBoundary;
import use_case.make_move.MakeMoveOutputData;
import use_case.new_game.NewGameOutputData;

import javax.swing.*;

public class MakeMovePresenter implements MakeMoveOutputBoundary {
    private final MakeMoveViewModel makeMoveViewModel;
    private ViewManagerModel viewManagerModel;

    public MakeMovePresenter(MakeMoveViewModel makeMoveViewModel, ViewManagerModel viewManagerModel) {
        this.makeMoveViewModel = makeMoveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MakeMoveOutputData makeMoveOutputData) {
        MakeMoveState makeMoveState = makeMoveViewModel.getState();
        makeMoveState.setGameBeingPlayed(makeMoveOutputData.getGameBeingPlayed());
        makeMoveState.setCol(makeMoveState.getCol());
        makeMoveState.setRow(makeMoveState.getRow());

        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareWrongMoveView(MakeMoveOutputData makeMoveOutputData) {
        MakeMoveState makeMoveState = makeMoveViewModel.getState();
        makeMoveState.setGameBeingPlayed(makeMoveOutputData.getGameBeingPlayed());
        makeMoveState.setCol(makeMoveState.getCol());
        makeMoveState.setRow(makeMoveState.getRow());
        makeMoveState.setUserInputWrong(true);
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);

    }

    @Override
    public void prepareEndLostView(MakeMoveOutputData makeMoveOutputData) {
        makeMoveViewModel.getState().setGameFinishedLost(true);
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareEndWonView(MakeMoveOutputData makeMoveOutputData) {
        makeMoveViewModel.getState().setGameFinishedWin(true);
        this.viewManagerModel.firePropertyChanged();

    }
}
