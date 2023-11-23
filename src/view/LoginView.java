package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.cancel.CancelController;
import interface_adapter.start.StartViewModel;

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

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login view";

    private final LoginViewModel loginViewModel;

    private final LoginController loginController;

    private final CancelController cancelController;



    // text input
    private final JTextField usernameInputField =  new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);

    // buttons on menu
    private final JButton login;
    private final JButton cancel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);

    public LoginView(LoginController loginController, LoginViewModel loginViewModel, CancelController cancelController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.cancelController = cancelController;

        loginViewModel.addPropertyChangeListener(this);

        this.setBackground(blue);

        JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);
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

        JPanel buttons = new JPanel();
        buttons.setBackground(darkblue);

        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(white);
        cancel.setFont(new Font("Verdana", Font.BOLD, 16));
        cancel.setForeground(darkblue);
        buttons.add(cancel);

        login = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        login.setBackground(blue);
        login.setFont(new Font("Verdana", Font.BOLD, 16));
        login.setForeground(white);
        buttons.add(login);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);

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


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
        else if (state.getUsername() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }
}


