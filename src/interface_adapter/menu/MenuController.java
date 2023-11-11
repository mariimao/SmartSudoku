package interface_adapter.menu;

import use_case.start.MenuInputBoundary;
import use_case.start.MenuInteractor;

public class MenuController {

    final MenuInputBoundary menuUseCaseInteractor;

    public MenuController(MenuInteractor menuUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
    }

    public void execute () {
        menuUseCaseInteractor.execute();
    }
}
