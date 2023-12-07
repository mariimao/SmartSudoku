package use_case.login;

/**
 * Class representing the input data of the Login
 */
public class LoginInputData {
    private final String username;
    private final String password;

    /**
     * Constructor for the LoginInputData object.
     *
     * @param username the username of the user
     * @param password the password entered by the user
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /* Getters and setters */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
