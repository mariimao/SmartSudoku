package use_case.signup;

/**
 * Class representing the input data of the Signup
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatpassword;

    /**
     * Constructor for the SignupInputData object.
     * @param username the username of the user
     * @param password the password entered by the user
     * @param repeatpassword the repeated password entered by the user
     */
    public SignupInputData(String username, String password, String repeatpassword) {
        this.username = username;
        this.password = password;
        this.repeatpassword = repeatpassword;
    }

    /* Getters and setters */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return this.repeatpassword;
    }
}
