package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the Signup which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class SignupView  extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "signup view";

    private final SignupViewModel signupViewModel;

    private final SignupController signupController;

    // text input
    private final JTextField usernameInputField =  new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    // buttons on menu
    private final JButton signup;
    private final JButton cancel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);

    private final Color white = Color.white;

    private final Color black = Color.black;

    /**
     * Constructor for Signup View
     * @param signupController the controller for signup usecase, is a SignupController object
     * @param signupViewModel the view model for signup usercase, is a SignupViewModel object
     */
    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        signupViewModel.addPropertyChangeListener(this);

        this.setBackground(blue);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        JLabel username = new JLabel(SignupViewModel.USERNAME_LABEL);
        username.setFont(new Font("Consolas", Font.ITALIC, 20));
        username.setForeground(darkblue);
        usernameInputField.setBorder(new BevelBorder(10, blue, black));
        usernameInputField.setBackground(white);
        usernameInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        usernameInputField.setForeground(darkblue);
        LabelTextPanel usernameInfo = new LabelTextPanel(username, usernameInputField);
        usernameInfo.setBackground(blue);
        this.add(usernameInfo);

        JLabel password = new JLabel(SignupViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Consolas", Font.ITALIC, 20));
        password.setForeground(darkblue);
        passwordInputField.setBorder(new BevelBorder(10, blue, black));
        passwordInputField.setBackground(white);
        passwordInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        passwordInputField.setForeground(darkblue);
        LabelTextPanel passwordInfo = new LabelTextPanel(password, passwordInputField);
        passwordInfo.setBackground(blue);
        this.add(passwordInfo);

        JLabel repeatPassword = new JLabel(SignupViewModel.REPEATPASSWORD_LABEL);
        repeatPassword.setFont(new Font("Consolas", Font.ITALIC, 20));
        repeatPassword.setForeground(darkblue);
        repeatPasswordInputField.setBorder(new BevelBorder(10, blue, black));
        repeatPasswordInputField.setBackground(white);
        repeatPasswordInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        repeatPasswordInputField.setForeground(darkblue);
        LabelTextPanel repeatPasswordInput = new LabelTextPanel(repeatPassword, repeatPasswordInputField);
        repeatPasswordInput.setBackground(blue);
        this.add(repeatPasswordInput);

        JPanel buttons = new JPanel();
        buttons.setBackground(darkblue);

        cancel = new CustomButton(SignupViewModel.CANCEL_BUTTON_LABEL, white, darkblue);
        buttons.add(cancel);
        signup = new CustomButton(SignupViewModel.SIGNUP_BUTTON_LABEL, blue, white);
        buttons.add(signup);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);


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
                            setVisible(false);
                            cancel.setVerifyInputWhenFocusTarget( false );
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
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}
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
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}
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

    }

    /**
     * Records the action performed
     * @param e the action that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * Records and notifies of any property change such as username or password errors
     * @param evt the propertychange event that is fired by the viewmodel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }
}
