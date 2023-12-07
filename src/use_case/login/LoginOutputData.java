package use_case.login;

/**
 * Class representing the output data for Login.
 */
public class LoginOutputData {
    private final String name;
    private final boolean useCaseFailed;

    /**
     * Constructor for an LoginOutputData object.
     *
     * @param username      is the username of the user.
     * @param useCaseFailed is a boolean that determines if the use case was successful
     */
    public LoginOutputData(String username, boolean useCaseFailed) {
        this.name = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * @return the username of the user
     */
    public String getName() {
        return name;
    }
}
