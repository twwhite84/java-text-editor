package nz.ac.massey.gui;

import lombok.Getter;
import lombok.Setter;
import nz.ac.massey.action.*;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI {

    /**
     * Internal window frame used for the application
     */
    @Getter
    private final JFrame frame;

    /**
     * Map of all registered actions
     */
    private final Map<String, TextEditorAction> registeredActions = new HashMap<>();

    /**
     * The current open file.
     * {@code null} if no open file
     */
    @Getter
    @Setter
    private File openFile;

    /**
     * Internal state if this editor instance has been saved to a file
     */
    @Getter
    private boolean saved;

    /**
     * Top menu bar of application
     */
    @Getter
    private final TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    @Getter
    private final TextEditorContentPane guiContentPane;

    public TextEditorGUI() {
        registerActions();

        // Setup UI in non ci environment
        if (System.getenv("GITHUB_ACTIONS") == null) {
            // When starting new instance, it is an "Untitled" file
            this.frame = new JFrame("Untitled*");

            guiMenuBar = new TextEditorMenuBar(this);
            guiContentPane = new TextEditorContentPane(this);

            init();
        } else {
            this.frame = null;
            this.guiContentPane = null;
            this.guiMenuBar = null;
        }
    }

    /**
     * Register all actions for the application
     */
    private void registerActions() {
        registerAction(new NewFileAction());
        registerAction(new OpenFileAction());
        registerAction(new SaveAction());
        registerAction(new SaveAsAction());
        registerAction(new ExitAction());
    }

    /**
     * Save the contents of the editor to the specified file
     * and set the current opened file to this file. Used on first save
     * or if wanting to create a new file from existing
     * 
     * @param file File object to be created
     */
    public void saveAs(File file) {
        // Assign open file
        this.openFile = file;

        // Update title of editor
        frame.setTitle(openFile.getName());

        // Now call save action to write bytes to that file
        save();
    }

    /**
     * Save the contents of the editor to the opened file. Must have
     * an open file or will be prompted to {@link #saveAs(File)}
     */
    public void save() {
        // If no open file, prompt to save as
        if (this.openFile == null) {
            runAction("Save As");
            return;
        }

        // Read bytes from text editor
        String content = guiContentPane.getTextArea().getText();

        // Save to file
        save(content);
    }

    /**
     * Save string content to the opened file
     *
     * @param content Content in string format
     */
    public void save(String content) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.openFile));
            fileWriter.write(content);
            fileWriter.close();

            // Set state to saved
            setSaved(true);
        } catch (IOException ex) {
            ex.printStackTrace();

            if (System.getenv("GITHUB_ACTIONS") == null) {
                // Error while saving
                JOptionPane.showMessageDialog(frame, "There was an error trying to save", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("There was an error trying to save");
            }
        }
    }

    /**
     * Open a file and populate the contents of the editor
     *
     * @param file The file object that was opened
     */
    public void openFile(File file) {
        this.openFile = file;
        if (System.getenv("GITHUB_ACTIONS") == null) {
            frame.setTitle(file.getName());
        }

        // Read contents of file into text area
        try {
            if (file.getName().toLowerCase().endsWith(".txt") || file.getName().toLowerCase().endsWith(".rtf")) {
                // This method works for basic text-based files, .txt, .rtf
                String fileContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
                guiContentPane.getTextArea().setText(fileContent);
            } else if (file.getName().toLowerCase().endsWith(".odt")) {
                // Process OpenDocument Text files (.odt)
                OdfTextDocument document = (OdfTextDocument) OdfDocument.loadDocument(file);

                // Buggy, does not format correctly *sigh*
                String textContent = document.getContentRoot().getTextContent();
                guiContentPane.getTextArea().setText(textContent);

                document.close();
            } else {
                if (System.getenv("GITHUB_ACTIONS") == null) {
                    // Not supported extension if somehow opened
                    JOptionPane.showMessageDialog(frame, "File type not supported", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.err.println("File type not supported");
                }
            }
        } catch (Exception ex) {
            if (System.getenv("GITHUB_ACTIONS") == null) {
                JOptionPane.showMessageDialog(frame, "There was an error attempting to open that file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("There was an error attempting to open that file");
            }
            ex.printStackTrace();
        }

        // Is saved when opening a file
        this.setSaved(true);
    }

    /**
     * Set internal save state of editor. Also updates title of window
     * to reflect state
     *
     * @param saved If is saved or not
     */
    public void setSaved(boolean saved) {
        this.saved = saved;

        if (System.getenv("GITHUB_ACTIONS") == null) {
            if (saved) {
                // Update title to saved state
                if (this.openFile != null)
                    frame.setTitle(this.openFile.getName());
            } else {
                if (this.openFile != null)
                    frame.setTitle(this.openFile.getName() + "*");
            }
        }
    }

    /**
     * exit current window
     */
    public void exit() {
        new ExitAction().performAction(this);
    }

    /**
     * Setup the main GUI for a new instance
     */
    private void init() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        frame.setJMenuBar(guiMenuBar);
        frame.setContentPane(guiContentPane);
        frame.setSize(900, 400);
        frame.setVisible(true);

        // Center on screen
        frame.setLocationRelativeTo(null);
    }

    /**
     * Attempt to run action with name for this editor instance
     *
     * @param name Name of the action
     */
    public void runAction(String name) {
        if (!registeredActions.containsKey(name)) {
            System.err.println("Action `" + name + "` is not registered");
            return;
        }

        // Run action
        TextEditorAction foundAction = registeredActions.get(name);
        foundAction.performAction(this);
    }

    /**
     * Register a new action to be run throughout the program
     *
     * @param action Action to be run
     */
    public void registerAction(TextEditorAction action) {
        registeredActions.put(action.getName(), action);
    }

}