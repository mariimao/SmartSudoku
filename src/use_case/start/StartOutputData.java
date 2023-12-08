package use_case.start;

/**
 * Class representing the output data for the start use case.
 */

public class StartOutputData {
    private final String interaction;

    /**
     * Constructor for a StartOutputData object.
     *
     * @param interaction is a string
     */

    public StartOutputData(String interaction) {
        this.interaction = interaction;
    }

    /**
     * @return the interaction
     */

    public String getInteracton() {
        return this.interaction;
    }
}
