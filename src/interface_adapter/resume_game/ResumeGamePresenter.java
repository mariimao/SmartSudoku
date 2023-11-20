package interface_adapter.resume_game;

import interface_adapter.ViewManagerModel;
import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.resume_game.ResumeGameOutputData;

import javax.swing.*;

public class ResumeGamePresenter implements ResumeGameOutputBoundary {
    private final ResumeGameViewModel resumeGameViewModel;
    private ViewManagerModel viewManagerModel;

    public ResumeGamePresenter(ResumeGameViewModel resumeGameViewModel, ViewManagerModel viewManagerModel) {
        this.resumeGameViewModel = resumeGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {
        // TODO: needs to be implemented once the game use case is implemented
        ResumeGameState resumeGameState = resumeGameViewModel.getState();
        resumeGameState.setPausedGame(resumeGameOutputData.getPausedGame());
        resumeGameState.setPastGames(resumeGameOutputData.getPausedGame().getPastStates());
        JOptionPane.showMessageDialog(null, "Game Successfully Resumed");   // for now I'll just use a popup
        this.viewManagerModel.setActiveViewName(resumeGameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ResumeGameState resumeGameState = resumeGameViewModel.getState();
        resumeGameState.setPauseGameError(error);
        resumeGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);   // for now I'll just use a popup



    }
}
