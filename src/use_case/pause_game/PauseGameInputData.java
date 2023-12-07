package use_case.pause_game;

import entity.board.GameState;

import java.util.LinkedList;

/**
 * Class representing the input data of the PauseGame use case.
 */
public class PauseGameInputData {
    final private String userName;
    final private GameState current_state;
    final private LinkedList<GameState> past_states;

    /**
     * Constructor for a PauseGameInputData object.
     *
     * @param userName     is a string representing the username
     * @param currentState is a GameState object of the current state
     * @param pastStates   is a LinkedList of all the past states
     */
    public PauseGameInputData(String userName, GameState currentState, LinkedList<GameState> pastStates) {
        this.userName = userName;
        current_state = currentState;
        past_states = pastStates;
    }

    /* ----- Getters and setters ----- */
    public String getUsername() {
        return userName;
    }

    public GameState getCurrent_state() {
        return current_state;
    }
}
