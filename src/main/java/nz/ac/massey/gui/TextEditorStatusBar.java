package nz.ac.massey.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Status bar to display some details of the editor
 */
@Getter
public class TextEditorStatusBar extends JPanel {

    /**
     * Labels for displaying information
     */
    @Getter
    private JLabel labelPosition, labelWordWrap, labelSyntax;

    public TextEditorStatusBar() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints statusBarConstraints;

        labelPosition = new JLabel("Line 1, Column 1");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 0;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        add(labelPosition, statusBarConstraints);

        labelWordWrap = new JLabel("Word Wrap: Off");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 1;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        add(labelWordWrap, statusBarConstraints);

        labelSyntax = new JLabel("Syntax: text/plain");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 2;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        add(labelSyntax, statusBarConstraints);
    }

}
