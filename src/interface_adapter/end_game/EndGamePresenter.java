package interface_adapter.end_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
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
            endGameState.setUser(endGameOutputData.getUser());
            endGameState.setFinalGame(endGameOutputData.getFinalGame());
            endGameState.setScore(endGameOutputData.getScore());
            this.viewManagerModel.setActiveViewName(endGameViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
            JOptionPane.showMessageDialog(null, "Congratulations, You Won. Try Playing Another Round");

        }

    public void prepareFailedGameView(EndGameOutputData endGameOutputData) {
        EndGameState endGameState = endGameViewModel.getState();
        endGameState.setUser(endGameOutputData.getUser());
        endGameState.setFinalGame(endGameOutputData.getFinalGame());
        endGameState.setScore(endGameOutputData.getScore());
        this.viewManagerModel.setActiveViewName(endGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, "You Lost The Game. Try Playing Another Round");

    }

        @Override
        public void prepareFailView(String error) {
            EndGameState endGameState = endGameViewModel.getState();
            endGameState.setEndGameError(error);
            endGameViewModel.firePropertyChanged();

        }
    }
