package use_case.resume_game;

import entity.board.GameState;
import entity.user.User;

import java.util.LinkedList;

public class ResumeGameInputData {
    final private String userName;

    public ResumeGameInputData(String userName) {
        this.userName = userName;
    }

    String getUsername() {
        return userName;
    }
}
