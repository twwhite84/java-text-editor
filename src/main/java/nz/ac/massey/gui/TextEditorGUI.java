package nz.ac.massey.gui;

import lombok.Getter;
import nz.ac.massey.action.NewFileAction;
import nz.ac.massey.action.OpenFileAction;
import nz.ac.massey.action.TextEditorAction;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.swing.*;
import java.io.File;
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
    protected final JFrame frame;

    /**
     * Map of all registered actions
     */
    private final Map<String, TextEditorAction> registeredActions = new HashMap<>();

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
    private final TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    @Getter
    private final TextEditorContentPane guiContentPane;

    public TextEditorGUI() {
        registerActions();

        // Setup UI in non ci environment
        if (System.getenv("GITHUB_ACTIONS") != null) {
            // When starting new instance, it is an "Untitled" file
            this.frame = new JFrame("Untitled");

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
    }

    /**
     * Open a file and populate the contents of the editor
     *
     * @param file The file object that was opened
     */
    public void openFile(File file) {
        this.openFile = file;
        if (System.getenv("GITHUB_ACTIONS") != null) {
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
                if (System.getenv("GITHUB_ACTIONS") != null) {
                    // Not supported extension if somehow opened
                    JOptionPane.showMessageDialog(frame, "File type not supported", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.err.println("File type not supported");
                }
            }
        } catch (Exception ex) {
            if (System.getenv("GITHUB_ACTIONS") != null) {
                JOptionPane.showMessageDialog(frame, "There was an error attempting to open that file", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("There was an error attempting to open that file");
            }
            ex.printStackTrace();
        }
    }

    /**
     * Setup the main GUI for a new instance
     */
    private void init() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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