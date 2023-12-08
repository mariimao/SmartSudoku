package use_case.resume_game;

/**
 * Class representing the input data of the resume game use case
 */
public class ResumeGameInputData {
    final private String userName;

    /**
     * Constructor for the ResumeGameInputData object.
     *
     * @param userName the username of the user
     */

    public ResumeGameInputData(String userName) {
        this.userName = userName;
    }

    /**
     * @return the username of the user
     */

    String getUsername() {
        return userName;
    }
}
