package interface_adapter.resume_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_game.PlayGameViewModel;
import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.resume_game.ResumeGameOutputData;

import javax.swing.*;

public class ResumeGamePresenter implements ResumeGameOutputBoundary {
    private final ResumeGameViewModel resumeGameViewModel;
    private ViewManagerModel viewManagerModel;
    private final PlayGameViewModel playGameViewModel;

    public ResumeGamePresenter(ResumeGameViewModel resumeGameViewModel, ViewManagerModel viewManagerModel, PlayGameViewModel playGameViewModel) {
        this.resumeGameViewModel = resumeGameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playGameViewModel = playGameViewModel;
    }

    @Override
    public void prepareSuccessView(ResumeGameOutputData resumeGameOutputData) {
        // TODO: needs to be implemented once the game use case is implemented
        ResumeGameState resumeGameState = resumeGameViewModel.getState();
        resumeGameState.setPausedGame(resumeGameOutputData.getPausedGame());
        resumeGameState.setPastGames(resumeGameOutputData.getPausedGame().getPastStates());
        playGameViewModel.getState().setCurrentGame(resumeGameState.getPausedGame());
        playGameViewModel.getState().setOldGameResumed(true);
        // JOptionPane.showMessageDialog(null, "Game Successfully Resumed");   // for now I'll just use a popup
        this.viewManagerModel.setActiveViewName(playGameViewModel.getViewName());
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
