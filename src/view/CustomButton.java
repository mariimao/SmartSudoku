package view;

import javax.swing.*;
import java.awt.*;

/**
 * Custom button which extends JButton.
 */

public class CustomButton extends JButton {
    public CustomButton(String text, Color background, Color foreground) {
        super(text.toUpperCase());
        this.setBackground(background);
        this.setFont(new Font("Verdana", Font.BOLD, 16));
        this.setForeground(foreground);
    }
}