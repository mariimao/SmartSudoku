package interface_adapter.signup;

/**
 * The state of Signup ViewModel
 */
public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    /**
     * Copy constructor of Signup State, makes a copy
     *
     * @param copy the copy of the state that it replicates from
     */
    public SignupState(SignupState copy) { // copy constructor
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.repeatPassword = copy.repeatPassword;
        this.repeatPasswordError = copy.repeatPasswordError;
    }

    /**
     * Default constructor for SignupState
     */
    public SignupState() {
    }

    /**
     * @return the username that that had been recently signed up
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username from the user for the View to display
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username error if use case was unsuccessful, where username already exists
     */
    public String getUsernameError() {
        return this.usernameError;
    }

    /**
     * Sets the usernameError for the user after unsuccessful use case
     *
     * @param usernameError the error description for when the username already exists
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * @return the password of the user that had recently been signed up
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password from the user
     *
     * @param password the password inputted by the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password error if use case was unsuccessful
     */
    public String getPasswordError() {
        return this.passwordError;
    }

    /**
     * Sets the password error after unsuccessful use case
     *
     * @param passwordError the error description for when passwords are not matching
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * @return the repeated password inputted by user to signup
     */
    public String getRepeatPassword() {
        return this.repeatPassword;
    }

    /**
     * Sets the repeat password after inputted by user
     *
     * @param repeatPassword the second time the user enters a password for verification
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * @return the repeatpassword error if use case was unsuccessful
     */
    public String getRepeatPasswordError() {
        return this.repeatPasswordError;
    }

    /**
     * Sets the repeat password error after unsuccessful use case
     *
     * @param repeatPasswordError the error description for when the passwords aren't matching
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }
}
