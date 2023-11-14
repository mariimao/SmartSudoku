package use_case.login;

import entity.User;
import interface_adapter.login.LoginState;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String username);

    User get(String username);

}
