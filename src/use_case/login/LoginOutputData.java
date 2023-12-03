package use_case.login;

public class LoginOutputData {
    private final String name;
    private final boolean useCaseFailed;

    public LoginOutputData(String name, boolean useCaseFailed) {
        this.name = name;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }
}
