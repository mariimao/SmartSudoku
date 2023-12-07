package use_case.menu;

/**
 * This class is the interface of the menu output boundary. It is implemented by MenuPresenter.
 */
public interface MenuOutputBoundary {

    /**
     * Called when the menu use case runs successfully - prepares a success view.
     */
    void prepareSuccessView();

    /**
     * Called when Leaderboard doesn't run successfully - prepares a fail view.
     * @param error is a String containing a description of the error
     */
    void prepareFailView(String error);
}
