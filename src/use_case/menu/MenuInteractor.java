package use_case.menu;

import use_case.menu.MenuUserDataAccessInterface;

public class MenuInteractor implements MenuInputBoundary {
    final MenuUserDataAccessInterface menuUserDataAccessInterface;
    final MenuOutputBoundary menuPresenter;

    public MenuInteractor(MenuUserDataAccessInterface menuUserDataAccessInterface,
                           MenuOutputBoundary menuPresenter) {
        this.menuUserDataAccessInterface = menuUserDataAccessInterface;
        this.menuPresenter = menuPresenter;
    }

    @Override
    public void execute() {
        // redirects the user to the menu
        menuPresenter.prepareSuccessView();
    }
}
