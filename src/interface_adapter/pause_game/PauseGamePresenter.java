package interface_adapter.pause_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.start.StartViewModel;
import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

public class PauseGamePresenter implements PauseGameOutputBoundary {
    private final StartViewModel startViewModel;
    private final MenuViewModel menuViewModel;
    private final PauseGameViewModel pauseGameViewModel;
    private ViewManagerModel viewManagerModel;

    public PauseGamePresenter(
            StartViewModel startViewModel, MenuViewModel menuViewModel, PauseGameViewModel pauseGameViewModel, ViewManagerModel viewManagerModel) {
        this.startViewModel = startViewModel;
        this.menuViewModel = menuViewModel;
        this.pauseGameViewModel = pauseGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PauseGameOutputData user) {
        this.viewManagerModel.setActiveViewName(pauseGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

    }
}
