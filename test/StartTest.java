import app.Main;
import app.StartUseCaseFactory;
import database.UserDAOTest;
import view.LoginView;
import view.SignupView;
import view.StartView;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class StartTest {

    public JPanel getStartViewLabels() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        return (JPanel) jp.getComponent(0);
    }

    @org.junit.Test
    public void testSignupButtonPresent() {
        Main.main(null);
        JPanel labels = getStartViewLabels();
        SignupView sv = (SignupView) labels.getComponent(1);
        JLabel button = (JLabel) sv.getComponent(0);
        assert(button.getText().equals("Sign Up"));
    }

    @org.junit.Test
    public void testLoginButtonPresent() {
        Main.main(null);
        JPanel labels = getStartViewLabels();
        LoginView sv = (LoginView) labels.getComponent(2);
        JLabel button = (JLabel) sv.getComponent(0);
        assert(button.getText().equals("Login"));
    }

    @org.junit.Test
    public void testStartViewText() {
        Main.main(null);
        JPanel labels = getStartViewLabels();
        StartView sv = (StartView) labels.getComponent(0);
        JLabel label = (JLabel) sv.getComponent(0);
        assert(label.getText().equals("smart sudoku"));
    }

    @org.junit.Test
    public void testStartViewText2() {
        Main.main(null);
        JPanel labels = getStartViewLabels();
        StartView sv = (StartView) labels.getComponent(0);
        JLabel label = (JLabel) sv.getComponent(1);
        assert(label.getText().equals("SUDOKU WITH A TWIST!"));
    }

    @org.junit.Test
    public void testButtonsWork() {
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();

        UserDAOTest userDAOTest = new UserDAOTest();

        StartView startView = StartUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getStartViewModel(), useCaseTestObjects.getSignupViewModel(),
                useCaseTestObjects.getLoginViewModel(), userDAOTest.getUserDAO());

        JPanel a = (JPanel) startView.getComponent(2);
        JButton signupButton = (JButton) a.getComponent(0);
        JButton loginButton = (JButton) a.getComponent(1);

        Main.main(null);
        signupButton.doClick();
        loginButton.doClick();
    }
}
