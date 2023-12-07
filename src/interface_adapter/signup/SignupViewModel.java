package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class for Spotify use case. Extends ViewModel Class.
 */
public class SignupViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up";

    public static final String SIGNUP_BUTTON_LABEL = "SIGNUP";
    public static final String CANCEL_BUTTON_LABEL = "BACK";
    public static final String USERNAME_LABEL = "   Enter Username";
    public static final String PASSWORD_LABEL = "   Enter Password";
    public static final String REPEATPASSWORD_LABEL = "Re-enter Password";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SignupState signupState = new SignupState();

    /**
     * Constructor for SignupViewModel, inherits from ViewModel
     */
    public SignupViewModel() {
        super("signup view");
    }

    /**
     * Fires property change to view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.signupState);
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
     * @return the signup state of the view model
     */
    public SignupState getSignupState() {
        return this.signupState;
    }

    /**
     * Sets Signup state for viewmodel to use
     *
     * @param signupState the state of signup use case
     */
    public void setSignupState(SignupState signupState) {
        this.signupState = signupState;
    }
}
