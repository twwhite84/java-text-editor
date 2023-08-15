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

    public TextEditorGUI() {
        guiMenuBar = new TextEditorMenuBar(this);
        guiContentPane = new TextEditorContentPane(this);
    }

    /**
     * Setup the main GUI for a new instance
     */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(guiMenuBar.getMenuBar());
        frame.setContentPane(guiContentPane.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

}