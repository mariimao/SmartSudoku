package use_case.signup;

public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatpassword;

    public SignupInputData(String username, String password, String repeatpassword) {
        this.username = username;
        this.password = password;
        this.repeatpassword = repeatpassword;
    }

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
