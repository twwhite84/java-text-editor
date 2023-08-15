package nz.ac.massey.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Holds all content of the editor and displays it
 */
public class TextEditorContentPane extends Container {

    /**
     * Injected instance of main GUI
     */
    private final TextEditorGUI gui;

    /**
     * Labels for status bar
     */
    private JLabel lblWordWrap, lblCurrentLine;

    /**
     * Status bar object
     */
    private JPanel statusBar;

    public TextEditorContentPane(TextEditorGUI gui) {
        this.gui = gui;

        init();
    }

    /**
     * Get instance of the content pane
     */
    public void init() {
        // main text area
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JTextArea textArea = new JTextArea(4, 30);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Resize text field to window
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;

        add(scrollPane, c);

        // status bar
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;

        statusBar = new JPanel();
        statusBar.setLayout(new GridBagLayout());

        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 0;
        d.ipady = 20;
        d.ipadx = 20;

        lblCurrentLine = new JLabel("Current Line: 0");
        statusBar.add(lblCurrentLine, d);

        d = new GridBagConstraints();
        d.gridx = 1;
        d.ipady = 20;
        d.ipadx = 20;
        lblWordWrap = new JLabel("Word Wrap: OFF");
        statusBar.add(lblWordWrap, d);

        add(statusBar, c);
    }

    public void toggleWrapIndicator(Boolean wrapEnabled) {
        if (wrapEnabled) lblWordWrap.setText("Word Wrap: ON");
        else if (!wrapEnabled) lblWordWrap.setText("Word Wrap: OFF");
    }
}