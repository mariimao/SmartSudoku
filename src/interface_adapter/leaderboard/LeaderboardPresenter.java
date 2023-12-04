package interface_adapter.leaderboard;

import interface_adapter.ViewManagerModel;
        import interface_adapter.menu.MenuViewModel;
        import use_case.leaderboard.LeaderboardOutputBoundary;
        import use_case.leaderboard.LeaderboardOutputData;

import javax.swing.*;

/**
 * Class representing the LeaderboardPresenter.
 * This class is responsible for updating the views.
 */
public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    private final MenuViewModel menuViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for a LeaderboardPresenter object.
     * @param viewManagerModel is a ViewManagerModel object
     * @param leaderboardViewModel is a LeaderboardViewModel object
     * @param menuViewModel is a MenuViewModel
     */
    public LeaderboardPresenter(ViewManagerModel viewManagerModel,
                                LeaderboardViewModel leaderboardViewModel,
                                MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.menuViewModel = menuViewModel;
    }

    /**
     * If the view type is Back, then a backView object is prepared.
     */
    @Override
    public void prepareBackView() {
        viewManagerModel.setActiveViewName(new MenuViewModel().getViewName());
        viewManagerModel.firePropertyChanged();
    }


    /**
     * Called when Leaderboard runs successfully - prepares a success view. This updates the state of the leaderboard
     * view model, and notifies the viewManagerModel.
     * @param leaderboardOutputData is an LeaderboardOutputData object
     */
    public void prepareSuccessView(LeaderboardOutputData leaderboardOutputData) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardState.setUsername(menuViewModel.getMenuState().getUsername());
        leaderboardState.setSortingMethod("rank");
        leaderboardState.setLeaderboard(leaderboardOutputData.getLeaderboard());
        this.leaderboardViewModel.setLeaderboardState(leaderboardState);
        this.leaderboardViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Called when Leaderboard doesn't run successfully - prepares a fail view. This notifies the leaderboardViewModel,
     * and calls a JDialog to display the error.
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardState.setError(error);
        leaderboardViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);
    }
}