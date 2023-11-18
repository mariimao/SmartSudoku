package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.signup.cancel.CancelViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView  extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "signup view";

    private final SignupViewModel signupViewModel;

    private final SignupController signupController;
    private final CancelController cancelController;

    // text input
    private final JTextField usernameInputField =  new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    // buttons on menu
    private final JButton signup;
    private final JButton cancel;


    public SignupView(SignupController menuController, SignupViewModel signupViewModel, CancelController cancelController) {
        this.signupViewModel = signupViewModel;
        this.signupController = menuController;
        this.cancelController = cancelController;

        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEATPASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();

        signup = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signup);

        cancel = new JButton(signupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        signup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signup)) {
                            SignupState currentState = signupViewModel.getSignupState();

                            signupController.execute(currentState.getUsername(),
                                                currentState.getPassword(),
                                                currentState.getRepeatPassword());
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            cancelController.execute();
                        }
                    }
                }
        );


        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getSignupState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setSignupState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getSignupState();
                        String text = passwordInputField.getText() + e.getKeyChar();
                        currentState.setPassword(text);
                        signupViewModel.setSignupState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getSignupState();
                        String text = repeatPasswordInputField.getText() + e.getKeyChar();
                        currentState.setRepeatPassword(text);
                        signupViewModel.setSignupState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }
}
