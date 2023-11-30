package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import use_case.menu.MenuOutputBoundary;

import javax.swing.*;

public class MenuPresenter implements MenuOutputBoundary {
    private final MenuViewModel menuViewModel;
    private ViewManagerModel viewManagerModel;
    public MenuPresenter(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel) {
        this.menuViewModel = menuViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView() {
        MenuState menuState = menuViewModel.getMenuState();
        this.viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);

    }
}
