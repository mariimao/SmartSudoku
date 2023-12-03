package view;

import app.Main;
import app.SignupUseCaseFactory;
import app.StartUseCaseFactory;
import data_access.UserDAO;
import entity.user.CommonUserFactory;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.UseCaseTestObjects;
import use_case.signup.SignupInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTest {

    private Component[] signupComponents;

    @Before
    public void init() throws Exception {
        UserDAO userDAO = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", new CommonUserFactory());
        UseCaseTestObjects useCaseTestObjects = new UseCaseTestObjects();

        StartView startView = StartUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getStartViewModel(), useCaseTestObjects.getSignupViewModel(),
                useCaseTestObjects.getLoginViewModel(), userDAO);

        JPanel a = (JPanel) startView.getComponent(2);
        JButton signupButton = (JButton) a.getComponent(0);

        Main.main(null);
        signupButton.doClick();

        JPanel signupView = SignupUseCaseFactory.create(useCaseTestObjects.getViewManagerModel(),
                useCaseTestObjects.getLoginViewModel(), useCaseTestObjects.getSignupViewModel(),
                useCaseTestObjects.getStartViewModel(), userDAO);

        assert signupView != null;
        signupComponents = signupView.getComponents();
    }

    @Test
    public void testBackButtonPresent() {
        JPanel buttons = (JPanel) signupComponents[4];
        JButton backButton = (JButton) buttons.getComponent(0);
        assert(backButton.getText().equals("BACK"));
    }

    @Test
    public void testSignupButtonPresent() {
        JPanel buttons = (JPanel) signupComponents[4];
        JButton backButton = (JButton) buttons.getComponent(1);
        assert(backButton.getText().equals("SIGNUP"));
    }

    @Test
    public void testSignupView() {
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        JPanel signupView = new SignupView(controller, viewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView); jf.pack(); jf.setVisible(true);
        view.LabelTextPanel userPanel = (view.LabelTextPanel) signupView.getComponent(1);
        view.LabelTextPanel pwdPanel = (view.LabelTextPanel) signupView.getComponent(2);
        JTextField userField = (JTextField) userPanel.getComponent(1);
        JPasswordField pwdField = (JPasswordField) pwdPanel.getComponent(1);


        KeyEvent userEvent = new KeyEvent(
                userField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        userPanel.dispatchEvent(userEvent);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        KeyEvent event = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        pwdPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getSignupState().getPassword());

        // move to the right in the password field, otherwise the event
        // will type the character as the first character instead of the last!
        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        pwdPanel.dispatchEvent(eventRight);

        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        pwdPanel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getSignupState().getPassword());

        // assert that the values are as expected.
        Assert.assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getSignupState().getPassword());
    }
}
