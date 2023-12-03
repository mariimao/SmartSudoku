package interface_adapter.make_move;

import entity.board.GameState;
import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import use_case.make_move.MakeMoveInputBoundary;
import use_case.make_move.MakeMoveOutputBoundary;
import use_case.make_move.MakeMoveOutputData;
import use_case.new_game.NewGameOutputData;

import javax.swing.*;

public class MakeMovePresenter implements MakeMoveOutputBoundary {
    private final MakeMoveViewModel makeMoveViewModel;
    private ViewManagerModel viewManagerModel;
    private MakeMoveState makeMoveState;

    public MakeMovePresenter(MakeMoveViewModel makeMoveViewModel, ViewManagerModel viewManagerModel) {
        this.makeMoveViewModel = makeMoveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public GameState prepareSuccessView(MakeMoveOutputData makeMoveOutputData) {
        MakeMoveState makeMoveState = makeMoveViewModel.getState();
        makeMoveState.setGameBeingPlayed(makeMoveOutputData.getGameBeingPlayed());
        makeMoveState.setCol(makeMoveState.getCol());
        makeMoveState.setRow(makeMoveState.getRow());

        // this.viewManagerModel.setActiveViewName(makeMoveViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        // JOptionPane.showMessageDialog(null, "New Game Successfully Created");   // for now I'll just use a popup
        return makeMoveState.getGameBeingPlayed();
    }

    @Override
    public void prepareFailView(String error) {


        makeMoveViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);

    }

}
