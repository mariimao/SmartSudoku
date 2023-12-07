package interface_adapter.resume_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_game.PlayGameViewModel;
import use_case.resume_game.ResumeGameOutputBoundary;
import use_case.resume_game.ResumeGameOutputData;

import javax.swing.*;

/**
 * Class of Presenter for resume game use case. Implements ResumeGameOutputBoundary
 */
public class ResumeGamePresenter implements ResumeGameOutputBoundary {
    private final ResumeGameViewModel resumeGameViewModel;
    private final PlayGameViewModel playGameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for ResumeGamePresenter
     *
     * @param resumeGameViewModel the resume game view model
     * @param viewManagerModel    the view manager model
     * @param playGameViewModel   the play game view model
     */
    public ResumeGamePresenter(ResumeGameViewModel resumeGameViewModel, ViewManagerModel viewManagerModel, PlayGameViewModel playGameViewModel) {
        this.resumeGameViewModel = resumeGameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playGameViewModel = playGameViewModel;
    }

    /**
     * Prepares success view
     *
     * @param resumeGameOutputData is a ResumeGameOutputData object
     */
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

    /**
     * Prepares fail view with error message
     *
     * @param error is a String containing a description of the error
     */
    @Override
    public void prepareFailView(String error) {
        ResumeGameState resumeGameState = resumeGameViewModel.getState();
        resumeGameState.setPauseGameError(error);
        resumeGameViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error);   // for now I'll just use a popup


    }
}
