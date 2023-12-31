package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameViewModel;
import interface_adapter.resume_game.ResumeGameViewModel;
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
 * View for the LoginView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login view";

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    private final PlayGameViewModel playGameViewModel;
    private final PauseGameViewModel pauseGameViewModel;
    private final ResumeGameViewModel resumeGameViewModel;

    private final MenuViewModel menuViewModel;

    // text input
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);

    // buttons on menu
    private final JButton login;
    private final JButton cancel;

    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = Color.white;
    private final Color black = Color.black;

    /**
     * Constructor for Login View
     *
     * @param loginController     the controller for login usecase, is a LoginController object
     * @param loginViewModel      the view model for login usecase, is a LoginViewModel object
     * @param menuViewModel       the view model for the menu usecase, is a MenuViewModel object
     * @param pauseGameViewModel  the view model for the pause usecase, is a PauseGameViewModel object
     * @param playGameViewModel   the view model for the play usecase, is a PlayGameViewModel object
     * @param resumeGameViewModel the view model for the resume usecase, is a ResumeGameViewModel object
     */
    public LoginView(LoginController loginController, LoginViewModel loginViewModel, PlayGameViewModel playGameViewModel,
                     PauseGameViewModel pauseGameViewModel, ResumeGameViewModel resumeGameViewModel,
                     MenuViewModel menuViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.playGameViewModel = playGameViewModel;
        this.pauseGameViewModel = pauseGameViewModel;
        this.resumeGameViewModel = resumeGameViewModel;
        this.menuViewModel = menuViewModel;

        loginViewModel.addPropertyChangeListener(this);

        this.setBackground(darkblue);

        JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(white);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10, 40, 10, 40)));
        this.add(title);

        JLabel username = new JLabel(SignupViewModel.USERNAME_LABEL);
        username.setFont(new Font("Consolas", Font.ITALIC, 20));
        username.setForeground(white);
        usernameInputField.setBorder(new BevelBorder(10, blue, black));
        usernameInputField.setBackground(white);
        usernameInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        usernameInputField.setForeground(darkblue);
        LabelTextPanel usernameInfo = new LabelTextPanel(username, usernameInputField);
        usernameInfo.setBackground(darkblue);
        this.add(usernameInfo);

        JLabel password = new JLabel(SignupViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Consolas", Font.ITALIC, 20));
        password.setForeground(white);
        passwordInputField.setBorder(new BevelBorder(10, blue, black));
        passwordInputField.setBackground(white);
        passwordInputField.setFont(new Font("Consolas", Font.PLAIN, 20));
        passwordInputField.setForeground(darkblue);
        LabelTextPanel passwordInfo = new LabelTextPanel(password, passwordInputField);
        passwordInfo.setBackground(darkblue);
        this.add(passwordInfo);

        JPanel buttons = new JPanel();
        buttons.setBackground(darkblue);

        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(white);
        cancel.setFont(new Font("Verdana", Font.BOLD, 16));
        cancel.setForeground(darkblue);
        buttons.add(cancel);

        login = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        login.setBackground(blue);
        login.setFont(new Font("Verdana", Font.BOLD, 16));
        login.setForeground(white);
        buttons.add(login);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10, 40, 10, 40)));
        this.add(buttons);

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            LoginState currentState = loginViewModel.getLoginState();
                            playGameViewModel.getState().setUserName(currentState.getUsername());
                            resumeGameViewModel.getState().setUserName(currentState.getUsername());
                            menuViewModel.getMenuState().setUsername(currentState.getUsername());
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
                            setVisible(false);
                            cancel.setVerifyInputWhenFocusTarget(false);
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

    /**
     * Records the action performed
     *
     * @param e the action that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Records and notifies of any property change such as username or password errors
     *
     * @param evt the propertychange event that is fired by the viewmodel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}


