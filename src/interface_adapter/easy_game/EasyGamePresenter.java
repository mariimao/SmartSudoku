package interface_adapter.easy_game;

import interface_adapter.ViewManagerModel;
import use_case.user_move.UserMoveOutputBoundary;
import use_case.user_move.UserMoveOutputData;

public class EasyGamePresenter implements UserMoveOutputBoundary {

    private final EasyGameViewModel easyGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public EasyGamePresenter(ViewManagerModel viewManagerModel,
                          EasyGameViewModel easyGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.easyGameViewModel = easyGameViewModel;

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

    }
}
