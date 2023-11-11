package interface_adapter.start;

import interface_adapter.ViewManagerModel;

import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;


import use_case.start.StartOutputBoundary;
import use_case.start.StartOutputData;
import view.StartView;

import javax.swing.text.View;

import java.awt.*;


public class StartPresenter implements StartOutputBoundary {

    private final StartViewModel startViewModel;

    private final MenuViewModel menuViewModel;

    private ViewManagerModel viewManagerModel;

    public StartPresenter (StartViewModel startViewModel, MenuViewModel menuViewModel, ViewManagerModel viewManagerModel) {
        this.startViewModel = startViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    public void prepareSuccessView() {

        // changes view to menu view
        MenuState menuState = menuViewModel.getMenuState();
        this.menuViewModel.setMenuState(menuState);
        menuViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(menuViewModel.getViewName());


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
