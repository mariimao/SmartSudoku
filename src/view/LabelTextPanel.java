package view;

import javax.swing.*;

/**
 * LabelTextPanel which extends JPanel.
 */

public class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
