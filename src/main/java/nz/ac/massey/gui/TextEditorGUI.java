package nz.ac.massey.gui;

import lombok.Getter;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI extends JFrame {

    /**
     * File > New class
     */
    protected TextEditorFileNew fileNew;

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
                // Not supported extension if somehow opened
                JOptionPane.showMessageDialog(this, "File type not supported", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "There was an error attempting to open that file", "Error", JOptionPane.ERROR_MESSAGE);
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