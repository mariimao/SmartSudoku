package interface_adapter.end_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;

import javax.swing.*;

public class EndGamePresenter implements EndGameOutputBoundary {
    private final MenuViewModel menuViewModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final EndGameViewModel endGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public EndGamePresenter(LeaderboardViewModel leaderboardViewModel, MenuViewModel menuViewModel,
                            EndGameViewModel endGameViewModel, ViewManagerModel viewManagerModel) {
            this.leaderboardViewModel = leaderboardViewModel;
            this.menuViewModel = menuViewModel;
            this.endGameViewModel = endGameViewModel;
            this.viewManagerModel = viewManagerModel;
        }

        @Override
        public void prepareSuccessView(EndGameOutputData endGameOutputData) {
            EndGameState endGameState = endGameViewModel.getState();
            endGameViewModel.setState(endGameState);
            endGameState.setUser(endGameOutputData.getUser());
            endGameState.setScore(endGameOutputData.getScore());
            viewManagerModel.setActiveViewName(endGameViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

        @Override
        public void prepareFailView(String error) {
            EndGameState endGameState = endGameViewModel.getState();
            endGameState.setEndGameError(error);
            endGameViewModel.firePropertyChanged();

        }
    }
}
