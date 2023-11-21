package use_case.new_game;

import entity.user.User;

public class NewGameInputData {
    final private User user;
    final private int difficulty;

    public NewGameInputData(User user, int difficulty) {
        this.user = user;
        this.difficulty = difficulty;
    }

    String getUsername() {
        if (user == null) {return null;}
        else {return user.getName();}
    }
    int getDifficulty() {return difficulty;}
    // TODO: figure out how song timer will fit in to this use case
}
