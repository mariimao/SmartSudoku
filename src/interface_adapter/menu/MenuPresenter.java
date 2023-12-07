package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import use_case.menu.MenuOutputBoundary;

import javax.swing.*;

/**
 * Class of Presenter for menu use case. Implements MenuOutputBoundary
 */
public class MenuPresenter implements MenuOutputBoundary {
    private final MenuViewModel menuViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for MenuPresenter
     *
     * @param menuViewModel    the menu view model
     * @param viewManagerModel the view manager model
     */
    public MenuPresenter(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel) {
        this.menuViewModel = menuViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view
     */
    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveViewName(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares fail view with error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);

    }
}
