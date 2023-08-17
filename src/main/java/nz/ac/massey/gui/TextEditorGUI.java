package nz.ac.massey.gui;

import lombok.Getter;

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
    private File openFile;

    /**
     * Top menu bar of application
     */
    @Getter
    private TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    @Getter
    private TextEditorContentPane guiContentPane;

    /**
     * File > New class
     */
    protected TextEditorFileNew fileNew;

    public TextEditorGUI() {
        // When starting new instance, it is an "Untitled" file
        super("Untitled");

        guiMenuBar = new TextEditorMenuBar(this);
        guiContentPane = new TextEditorContentPane(this);
        fileNew = new TextEditorFileNew();

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
        // @todo Support .rtf, .odt and code files
        try {
            if (file.getName().toLowerCase().endsWith(".txt") || file.getName().toLowerCase().endsWith(".rtf")) {
                // This method works for basic text-based files, .txt, .rtf
                String fileContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
                guiContentPane.getTextArea().setText(fileContent);
            } else if (file.getName().toLowerCase().endsWith(".odt")) {
                // Process OpenDocument Text files
            } else {
                // Not supported extension if somehow opened
                JOptionPane.showMessageDialog(this, "File type not supported", "Error", JOptionPane.ERROR_MESSAGE);
            }
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