package interface_adapter.new_game;

import entity.board.GameState;
import entity.user.User;

/**
 * The state of NewGame ViewModel
 */
public class NewGameState {
    private String errorMessage = "";
    private GameState game = null;
    private User user = null;
    private int difficulty = 1; // will make an easyboard automatically if not otherwise instructed

    private String searchName = "";
    private String userName = "";

    /**
     * Constuctor for NewGameState
     */
    public NewGameState() {
    }

    public GameState getGame() {
        return game;
    }

    // below are the getters and setters
    public void setGame(GameState gameState) {
        game = gameState;
    }

    public String getNewGameError() {
        return errorMessage;
    }

    public void setNewGameError(String e) {
        errorMessage = e;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
}
