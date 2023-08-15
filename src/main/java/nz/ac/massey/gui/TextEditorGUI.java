package nz.ac.massey.gui;

import javax.swing.*;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI extends JFrame {

    /**
     * Top menu bar of application
     */
    protected TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    protected TextEditorContentPane guiContentPane;

    public TextEditorGUI() {
        // When starting new instance, it is an "Untitled" file
        super("Untitled");

        guiMenuBar = new TextEditorMenuBar(this);
        guiContentPane = new TextEditorContentPane(this);

        init();
    }

    /**
     * Setup the main GUI for a new instance
     */
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(guiMenuBar);
        setContentPane(guiContentPane);
        setSize(900, 400);
        setVisible(true);

        // Center on screen
        setLocationRelativeTo(null);
    }

}