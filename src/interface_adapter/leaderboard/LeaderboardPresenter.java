package interface_adapter.leaderboard;

import interface_adapter.ViewManagerModel;
        import interface_adapter.login.LoginState;
        import interface_adapter.menu.MenuState;
        import interface_adapter.menu.MenuViewModel;
        import interface_adapter.signup.SignupState;
        import use_case.leaderboard.LeaderboardOutputBoundary;
        import use_case.leaderboard.LeaderboardOutputData;
        import view.LeaderboardView;

import javax.swing.*;
import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final MenuViewModel menuViewModel;

    private final LeaderboardViewModel leaderboardViewModel;

    private final ViewManagerModel viewManagerModel;
    public LeaderboardPresenter(ViewManagerModel viewManagerModel,
                                LeaderboardViewModel leaderboardViewModel,
                                MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.menuViewModel = menuViewModel;
    }

    public void prepareSuccessView(LeaderboardOutputData leaderboardOutputData) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardViewModel.setLeaderboardState(leaderboardState);
        leaderboardViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardState.setError(error);
        leaderboardViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);
    }
}