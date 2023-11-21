package use_case.signup;

import entity.user.User;

public interface SignupUserDataAccessInterface {
    boolean existsbyName(String username);

    void addUser(User user);
}
