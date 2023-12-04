package use_case.login;

/**
 * Class representing the InputBoundary for the Login usecase. This class is implemented by the LoginInteractor.
 */
public interface LoginInputBoundary {

    /**
     * Executes the Login use case.
     * @param loginInputData is a loginInputData object
     */
    void execute(LoginInputData loginInputData);
}
