package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login view";

    private final LoginViewModel loginViewModel;

    private final LoginController loginController;

    private final CancelController cancelController;



    // text input
    private final JTextField usernameInputField =  new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    // buttons on menu
    private final JButton login;
    private final JButton cancel;


    public LoginView(LoginController loginController, LoginViewModel loginViewModel, CancelController cancelController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.cancelController = cancelController;

        loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();

        login = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);

        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            LoginState currentState = loginViewModel.getLoginState();

                            loginController.execute(currentState.getUsername(),
                                    currentState.getPassword());
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
                        LoginState currentState = loginViewModel.getLoginState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        loginViewModel.setLoginState(currentState);
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
                        LoginState currentState = loginViewModel.getLoginState();
                        String text = passwordInputField.getText() + e.getKeyChar();
                        currentState.setPassword(text);
                        loginViewModel.setLoginState(currentState);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getPassword() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }
}


