package interface_adapter.signup;

import interface_adapter.ViewModel;
import interface_adapter.start.StartState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up";

    public static final String SIGNUP_BUTTON_LABEL = "SIGNUP";
    public static final String CANCEL_BUTTON_LABEL = "BACK";
    public static final String USERNAME_LABEL = "   Enter Username";
    public static final String PASSWORD_LABEL = "   Enter Password";
    public static final String REPEATPASSWORD_LABEL = "Re-enter Password";

    private SignupState signupState = new SignupState();

    public SignupViewModel() {
        super("signup view");
    }

    public void setSignupState(SignupState signupState) {
        this.signupState = signupState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.signupState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getSignupState() { return this.signupState; }
}
