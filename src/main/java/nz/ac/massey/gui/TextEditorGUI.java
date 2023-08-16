package nz.ac.massey.gui;

import javax.swing.*;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI {

    /**
     * Top menu bar of application
     */
    protected TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    protected TextEditorContentPane guiContentPane;

    /**
     * File > New class
     */
    protected TextEditorFileNew fileNew;

    public TextEditorGUI() {
        guiMenuBar = new TextEditorMenuBar(this);
        guiContentPane = new TextEditorContentPane(this);
        fileNew = new TextEditorFileNew();
    }

    /**
     * Setup the main GUI for a new instance
     */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setJMenuBar(guiMenuBar.getMenuBar());
        frame.setContentPane(guiContentPane.getContentPane());
        frame.setSize(900, 400);
        frame.setVisible(true);

        // Center on screen
        frame.setLocationRelativeTo(null);
    }

}