package interface_adapter.login;

import entity.user.User;

/**
 * The LoginState class. Represents the state of the login use case.
 */
public class LoginState {
    private String username = "";
    private User user = null;
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * The constructor for LoginState.
     */
    public LoginState() {}

    //Below are the getters and setters //
    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }
    public User getUser() {return user;}


    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setUser(User user) {this.user = user;}
}

