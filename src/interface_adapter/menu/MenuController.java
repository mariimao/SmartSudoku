package interface_adapter.menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;

public class MenuController {

    final MenuInputBoundary menuUseCaseInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
    }

    public void execute () {
        menuUseCaseInteractor.execute();
    }
}
