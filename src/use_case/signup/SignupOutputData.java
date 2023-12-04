package use_case.signup;

/**
 * Class representing the output data for Signup.
 */
public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;

    /**
     * Constructor for an SignupOutputData object.
     * @param username is the username of the user.
     * @param useCaseFailed is a boolean that determiens if the use case was successful
     */
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

}
