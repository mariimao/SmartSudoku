package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class ResumeGameInputData {
    final private User user;

    public ResumeGameInputData(User user) {
        this.user = user;
    }

    String getUsername() {return user.getName();}
    GameState getcurrentState() {return user.getPausedGame();}
    LinkedList<GameState> getpastStates() {return user.getPausedGame().getPastStates();}
}
