package use_case.pause_game;
import entity.*;

import java.util.LinkedList;

public class PauseGameInputData {
    final private User user;
    final private GameState current_state;
    final private LinkedList<GameState> past_states;

    public PauseGameInputData(User user, GameState currentState, LinkedList<GameState> pastStates) {
        this.user = user;
        current_state = currentState;
        past_states = pastStates;
    }

    String getUsername() {return user.getName();}
    GameState getCurrent_state() {return current_state;}
    LinkedList<GameState> getPast_states() {return past_states;}
}
