package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class ResumeGameInputData {
    final private User user;
    final private GameState current_state;
    final private LinkedList<GameState> past_states;

    public ResumeGameInputData(User user, GameState currentState, LinkedList<GameState> pastStates) {
        this.user = user;
        current_state = currentState;
        past_states = pastStates;
    }

    String getUsername() {return user.getName();}
    GameState getCurrent_state() {return current_state;}
    LinkedList<GameState> getPast_states() {return past_states;}
}
