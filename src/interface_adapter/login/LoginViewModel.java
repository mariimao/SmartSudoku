package interface_adapter.login;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Welcome to Sudoku";

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "cancel";

    private LoginState loginState = new LoginState();

    public LoginViewModel() {
        super("login view");
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.loginState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getLoginState() { return this.loginState; }
}
