package interface_adapter.easy_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import use_case.user_move.UserMoveOutputBoundary;
import use_case.user_move.UserMoveOutputData;

public class EasyGamePresenter implements UserMoveOutputBoundary {

    private final EasyGameViewModel easyGameViewModel;
    private final ViewManagerModel viewManagerModel;
    private final EndGameViewModel endGameViewModel;

    public EasyGamePresenter(ViewManagerModel viewManagerModel,
                          EasyGameViewModel easyGameViewModel, EndGameViewModel endGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.easyGameViewModel = easyGameViewModel;
        this.endGameViewModel = endGameViewModel;

    }

    @Override
    public void prepareSuccessView(UserMoveOutputData userMoveOutputData) {
        EasyGameState easyGameState = easyGameViewModel.getState();
        easyGameState.setEasyGame(userMoveOutputData.getGameState());
        viewManagerModel.setActiveViewName(easyGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        EasyGameState easyGameState = easyGameViewModel.getState();
        easyGameState.setEasyGameError(error);
        easyGameViewModel.firePropertyChanged();
    }

    @Override
    public void prepareEndView(UserMoveOutputData userMoveOutputData) {
        EndGameState endGameState = endGameViewModel.getState();
        endGameViewModel.setState(endGameState);
        endGameViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(endGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
