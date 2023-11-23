package view;

import interface_adapter.signup.SignupState;
import interface_adapter.start.StartController;
import interface_adapter.start.StartState;
import interface_adapter.start.StartViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import view.CustomButton;

public class StartView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Start";

    // adapters for start
    private final StartViewModel startViewModel;
    private final StartController startController;

    // buttons on menu
    private final JButton signup;
    private final JButton login;
    private final Color blue = new Color(97, 150, 242);
    private final Color darkblue = new Color(50, 78, 156);
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);


    public StartView(StartController startController, StartViewModel startViewModel) {
        // initializes start
        this.startViewModel = startViewModel;
        this.startController = startController;

        startViewModel.addPropertyChangeListener(this);

        this.setBackground(white);

        JLabel title = new JLabel(StartViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 90));
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        JLabel welcome = new JLabel(StartViewModel.WELCOME_LABEL);
        welcome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        welcome.setFont(new Font("Verdana", Font.ITALIC, 20));
        welcome.setForeground(darkblue);
        welcome.setBorder(new CompoundBorder(welcome.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(welcome);

        JPanel buttons = new JPanel();
        buttons.setBackground(darkblue);

        signup = new CustomButton(startViewModel.SIGNUP_BUTTON_LABEL, blue, white);
        login = new CustomButton(startViewModel.LOGIN_BUTTON_LABEL, white, blue);
        buttons.add(signup);
        buttons.add(login);
        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(buttons);

        signup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signup)) {
                            startController.execute("Signup");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showConfirmDialog(this, " not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //StartState state = (StartState) evt.getNewValue();

    }
}
