package use_case.new_game;

/**
 * Class representing the input data of NewGame.
 */
public class NewGameInputData {
    final private String userName;
    final private int difficulty;

    /**
     * Constructor for a object.
     * @param userName is a String of the username
     * @param difficulty is the int representing the difficulty (1 = easy, 2 = hard)
     */
    public NewGameInputData(String userName, int difficulty) {
        this.userName = userName;
        this.difficulty = difficulty;
    }

    /* ----- Getters and setters ----- */
    String getUsername() {return userName;}
    int getDifficulty() {return difficulty;}
}
