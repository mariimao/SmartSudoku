package view;

import interface_adapter.start.StartController;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartView  extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "start view";

    // adapters for start
    private final StartViewModel startViewModel;
    private final StartController startController;

    // buttons on menu
    private final JButton signup;
    private final JButton login;

    public StartView(StartController startController, StartViewModel startViewModel) {

        // initializes start
        this.startViewModel = startViewModel;
        this.startController = startController;

        startViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(StartViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        signup = new JButton(startViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signup);

        login = new JButton(startViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);

        signup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signup)) {
                            startController.execute("Signin");
                        }
                    }
                }
        );

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            startController.execute("Login");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
