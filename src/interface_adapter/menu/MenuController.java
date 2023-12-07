package interface_adapter.menu;

import use_case.menu.MenuInputBoundary;

/**
 * The class for MenuController. Acts as controller to send information to the interactor.
 */
public class MenuController {

    final MenuInputBoundary menuUseCaseInteractor;

    /**
     * Constructor for MenuController
     *
     * @param menuUseCaseInteractor the interactor, a MenuInputBoundary type
     */
    public MenuController(MenuInputBoundary menuUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
    }

    /**
     * Calls the interactor to execute. No input data is needed.
     */
    public void execute() {
        menuUseCaseInteractor.execute();
    }
}
