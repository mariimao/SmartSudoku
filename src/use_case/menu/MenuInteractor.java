package use_case.menu;

/**
 * Class representing the interactor for the menu usecase. This class implements the MenuInputBoundary.
 */
public class MenuInteractor implements MenuInputBoundary {
    final MenuUserDataAccessInterface menuUserDataAccessInterface;
    final MenuOutputBoundary menuPresenter;

    /**
     * Constructor for the MenuInteractor object.
     *
     * @param menuUserDataAccessInterface is a MenuUserDataAccessInterface object
     * @param menuPresenter               is a MenuOutputBoundary object
     */
    public MenuInteractor(MenuUserDataAccessInterface menuUserDataAccessInterface,
                          MenuOutputBoundary menuPresenter) {
        this.menuUserDataAccessInterface = menuUserDataAccessInterface;
        this.menuPresenter = menuPresenter;
    }

    /**
     * This function redirects the user to the menu.
     */
    @Override
    public void execute() {
        menuPresenter.prepareSuccessView();
    }
}
