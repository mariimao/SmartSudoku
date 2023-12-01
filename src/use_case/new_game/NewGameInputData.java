package use_case.new_game;

import entity.user.User;

public class NewGameInputData {
    final private String userName;
    final private int difficulty;


    public NewGameInputData(String userName, int difficulty) {
        this.userName = userName;
        this.difficulty = difficulty;
    }

    String getUsername() {return userName;}
    int getDifficulty() {return difficulty;}
}
