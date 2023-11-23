package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text, Color background, Color foreground) {
        super(text.toUpperCase());
        this.setBackground(background);
        this.setFont(new Font("Verdana", Font.BOLD, 16));
        this.setForeground(foreground);
    }
}