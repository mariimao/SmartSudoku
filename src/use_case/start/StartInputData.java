package use_case.start;

/**
 * Class representing the input data of the start use case
 */

public class StartInputData {
    private final String interaction;

    /**
     * Constructor for the StartInputData object.
     *
     * @param interaction the interaction of the user
     */

    public StartInputData(String interaction) {
        this.interaction = interaction;
    }

    /**
     * @return the interaction
     */

    public String getInteracton() {
        return this.interaction;
    }
}
