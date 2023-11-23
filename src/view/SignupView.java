package view;

import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.signup.cancel.CancelViewModel;

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

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);

    private final Color white = Color.white;

    private final Color black = Color.black;

    public SignupView(SignupController menuController, SignupViewModel signupViewModel, CancelController cancelController) {
        this.signupViewModel = signupViewModel;
        this.signupController = menuController;
        this.cancelController = cancelController;

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

        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(white);
        cancel.setFont(new Font("Verdana", Font.BOLD, 16));
        cancel.setForeground(darkblue);
        buttons.add(cancel);

        signup = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signup.setBackground(blue);
        signup.setFont(new Font("Verdana", Font.BOLD, 16));
        signup.setForeground(white);
        buttons.add(signup);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);

//        LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
//        LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
//        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.REPEATPASSWORD_LABEL), repeatPasswordInputField);

//        JPanel buttons = new JPanel();

//        signup = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
//        buttons.add(signup);
//
//        cancel = new JButton(signupViewModel.CANCEL_BUTTON_LABEL);
//        buttons.add(cancel);


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
