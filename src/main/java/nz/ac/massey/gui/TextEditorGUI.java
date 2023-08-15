package nz.ac.massey.gui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI extends JFrame {

    /**
     * The current open file.
     * {@code null} if no open file
     */
    @Getter
    @Setter
    private File openFile;

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
     * Open a file and populate the contents of the editor
     *
     * @param file The file object that was opened
     */
    public void openFile(File file) {
        this.openFile = file;
        setTitle(file.getName());

        // Read contents of file into text area
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
            guiContentPane.getTextArea().setText(fileContent);
        } catch (IOException ex) {
            // TODO: Show error prompt
            System.err.println("There was an error attempting to open that file");
            ex.printStackTrace();
        }
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