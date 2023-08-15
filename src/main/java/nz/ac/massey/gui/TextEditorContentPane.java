package nz.ac.massey.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Holds all content of the editor and displays it
 */
public class TextEditorContentPane {

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
    }

    /**
     * Get instance of the content pane
     * <p>
     * TODO: Convert this class to Container object
     */
    public Container getContentPane() {
        // main text area
        Container pane = new Container();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;

        JTextArea textArea = new JTextArea(40, 120);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.add(scrollPane, c);

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

        pane.add(statusBar, c);

        return pane;
    }

    public void toggleWrapIndicator(Boolean wrapEnabled) {
        if (wrapEnabled) lblWordWrap.setText("Word Wrap: ON");
        else if (!wrapEnabled) lblWordWrap.setText("Word Wrap: OFF");
    }
}