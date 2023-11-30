package use_case.pause_game;
import entity.board.GameState;

import java.util.LinkedList;

public class PauseGameInputData {
    final private String userName;
    final private GameState current_state;
    final private LinkedList<GameState> past_states;

    public PauseGameInputData(String userName, GameState currentState, LinkedList<GameState> pastStates) {
        this.userName = userName;
        current_state = currentState;
        past_states = pastStates;
    }

    String getUsername() {return userName;}
    GameState getCurrent_state() {return current_state;}
    LinkedList<GameState> getPast_states() {return past_states;}
}
