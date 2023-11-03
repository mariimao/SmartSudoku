package interface_adapter.start;

import interface_adapter.ViewManagerModel;
import use_case.start.StartOutputBoundary;
import use_case.start.StartOutputData;
import view.StartView;

import javax.swing.text.View;

public class StartPresenter implements StartOutputBoundary {

    private final StartViewModel startViewModel;

    private ViewManagerModel viewManagerModel;

    public StartPresenter (StartViewModel startViewModel, ViewManagerModel viewManagerModel) {
        this.startViewModel = startViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView() {
        StartState startState = startViewModel.getStartState();
        this.startViewModel.setStartState(startState);
        startViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        startViewModel.firePropertyChanged();
    }
}
