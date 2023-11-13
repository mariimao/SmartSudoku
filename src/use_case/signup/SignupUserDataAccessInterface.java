package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsbyName(String username);

    void addUser(User user);
}
