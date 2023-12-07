package interface_adapter.menu;

/**
 *  The state of Menu ViewModel
 */
public class MenuState {

    private String username = "";

    /**
     * Copy constructor, makes a copy
     * @param copy the copy we want to replicate from
     */
    public MenuState(MenuState copy) {
        username = copy.username;
    }

    /**
     * Default constuctor for MenuState
      */
    public MenuState() {}

    /**
     * below are the getters and setters
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}