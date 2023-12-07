package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The Login View Model class. Extends ViewModel Class.
 */
public class LoginViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Login";

    public static final String LOGIN_BUTTON_LABEL = "LOG IN";
    public static final String CANCEL_BUTTON_LABEL = "BACK";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LoginState loginState = new LoginState();

    /**
     * Constructor for LoginViewModel
     */
    public LoginViewModel() {
        super("login view");
    }

    /**
     * Fires property change to view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.loginState);
    }

    /**
     * Adds propertychangelistener for support
     *
     * @param listener the listener that listens for property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return the start state of the view model
     */
    public LoginState getLoginState() {
        return this.loginState;
    }

    /**
     * Sete the loginState to update view model
     *
     * @param loginState the state of login
     */
    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }
}
