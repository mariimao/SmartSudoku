package use_case.signup;

import entity.user.User;

public interface SignupUserDataAccessInterface {

    void addUser(User user);

    boolean existsByName(String username);

    void delete(String name);
}
