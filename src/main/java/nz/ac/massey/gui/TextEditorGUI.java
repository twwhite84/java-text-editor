package nz.ac.massey.gui;

import lombok.Getter;
import lombok.Setter;
import nz.ac.massey.PDFUtils;
import nz.ac.massey.config.TextEditorConfig;
import nz.ac.massey.action.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is the main instance of the GUI for the text editor. It will hold all
 * state of the editor window as multiple of these windows can be created.
 */
public class TextEditorGUI {

    /**
     * Config used for the editor
     */
    @Getter
    private final TextEditorConfig config;

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
     * Top menu bar of application
     */
    @Getter
    private final TextEditorMenuBar guiMenuBar;

    /**
     * Main content pane of application
     */
    @Getter
    private final TextEditorContentPane guiContentPane;

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
     * Internal state of the text of the editor. Used for easier
     * manipulation and accessing in the CI environment.
     */
    @Getter
    private String content;

    public TextEditorGUI(TextEditorConfig config) {
        this.config = config;

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
     * Set the content of the text editor
     */
    public void setContent(String content) {
        this.setContent(content, true);
    }

    /**
     * Set the content of the text editor
     *
     * @param content        Content as string
     * @param updateTextArea If this will update the text area pane as well
     */
    public void setContent(String content, boolean updateTextArea) {
        // Update internal content
        this.content = content;

        // Update text area
        if (System.getenv("GITHUB_ACTIONS") == null && updateTextArea) {
            getGuiContentPane().getTextArea().setText(content);
        }
    }

    /**
     * Register all actions for the application
     */
    private void registerActions() {
        registerAction(new NewFileAction());
        registerAction(new OpenFileAction());
        registerAction(new SearchAction());
        registerAction(new SaveAction());
        registerAction(new SaveAsAction());
        registerAction(new ExitAction());
        registerAction(new AboutAction());
        registerAction(new TimeDateAction());
    }

    /**
     * Return a PDF file saved with the content of the editor.
     * Should be used in a try-with-resources statement to auto close the file
     *
     * @return The PDF file
     */
    public PDDocument getPdfFile() {
        PDDocument doc = new PDDocument();
        String content = guiContentPane.getTextArea().getText();

        try {
            // Create PDF
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            // Font config
            PDFont pdfFont = PDType1Font.HELVETICA;
            float fontSize = 14;
            float leading = 1.5f * fontSize;

            // Get dimensions of PDF page
            PDRectangle mediabox = page.getMediaBox();
            float margin = 72;
            float width = mediabox.getWidth() - 2 * margin;
            float startX = mediabox.getLowerLeftX() + margin;
            float startY = mediabox.getUpperRightY() - margin;

            // Split content between newlines and perform an auto
            // line split when line is longer then document
            List<String> lines = new ArrayList<>();
            String[] splitLines = content.split("\n");
            for (String text : splitLines) {
                int lastSpace = -1;
                if (text.length() > 0) {
                    while (text.length() > 0) {
                        int spaceIndex = text.indexOf(' ', lastSpace + 1);
                        if (spaceIndex < 0)
                            spaceIndex = text.length();
                        String subString = PDFUtils.santiseString(text.substring(0, spaceIndex));
                        float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                        if (size > width) {
                            if (lastSpace < 0)
                                lastSpace = spaceIndex;
                            subString = text.substring(0, lastSpace);
                            lines.add(subString);
                            text = text.substring(lastSpace).trim();
                            lastSpace = -1;
                        } else if (spaceIndex == text.length()) {
                            lines.add(text);
                            text = "";
                        } else {
                            lastSpace = spaceIndex;
                        }
                    }
                } else {
                    // If blank line, print blank line
                    lines.add(" ");
                }
            }

            // Start cursor
            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(startX, startY);

            float currentY = startY;

            // For each line to write
            for (String line : lines) {
                // Remove illegal text
                line = PDFUtils.santiseString(line);

                // Go down document
                currentY -= leading;

                // If goes below page
                if (currentY <= margin) {
                    contentStream.endText();
                    contentStream.close();

                    // Start on new page
                    PDPage new_Page = new PDPage();
                    doc.addPage(new_Page);
                    contentStream = new PDPageContentStream(doc, new_Page);
                    contentStream.beginText();
                    contentStream.setFont(pdfFont, fontSize);
                    contentStream.newLineAtOffset(startX, startY);
                    currentY = startY;
                }

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();
        } catch (Exception ex) {
            System.err.println("There was an error converting file to PDF");
            ex.printStackTrace();
        }

        return doc;
    }

    /**
     * Save the contents of the editor to the specified file
     * and set the current opened file to this file. Used on first save
     * or if wanting to create a new file from existing
     *
     * @param file File object to be created
     */
    public boolean saveAs(File file) {
        // Assign open file
        // Not for PDF as that is exporting
        if (!file.getName().endsWith(".pdf")) {
            this.openFile = file;
        }

        if (!file.getName().endsWith(".pdf")) {
            // Update title of editor
            frame.setTitle(openFile.getName());

            // Now call save action to write bytes to that file
            return save();
        } else if (file.getName().endsWith(".pdf")) {
            // "Export" to PDF format
            try (PDDocument document = getPdfFile()) {
                document.save(file.getPath());
                return true;
            } catch (IOException ex) {
                if (System.getenv("GITHUB_ACTIONS") == null) {
                    // Error while saving
                    JOptionPane.showMessageDialog(frame, "Error saving file to PDF format", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    System.err.println("Error saving file to PDF format");
                }
                ex.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Save the contents of the editor to the opened file. Must have
     * an open file or will be prompted to {@link #saveAs(File)}
     */
    public boolean save() {
        // If no open file, prompt to save as
        if (this.openFile == null) {
            return runAction("Save As");
        }

        // Read bytes from text editor
        String content = guiContentPane.getTextArea().getText();

        // Save to file
        return save(content);
    }

    /**
     * Save string content to the opened file
     *
     * @param content Content in string format
     */
    public boolean save(String content) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.openFile));
            fileWriter.write(content);
            fileWriter.close();

            // Set state to saved
            setSaved(true);

            if (System.getenv("GITHUB_ACTIONS") == null) {
                // Set syntax highlighting
                String syntax = "text/" + Optional.of(openFile.getName())
                        .filter(f -> f.contains("."))
                        .map(f -> f.substring(openFile.getName().lastIndexOf(".") + 1)).get();
                getGuiContentPane().setSyntax(syntax);
            }

            return true;
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

        return false;
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
            if (file.getName().toLowerCase().endsWith(".odt")) {
                // Process OpenDocument Text files (.odt)
                OdfTextDocument document = (OdfTextDocument) OdfDocument.loadDocument(file);

                // Get base text nodes of documents and build content
                StringBuilder textContent = new StringBuilder();
                NodeList nodes = document.getContentRoot().getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    textContent.append(node.getTextContent());
                    if (i != nodes.getLength() - 1) textContent.append("\n");
                }

                // Basic display of content
                setContent(textContent.toString());

                document.close();
            } else {
                // This method works for any other text-based files
                String fileContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
                setContent(fileContent);

                if (System.getenv("GITHUB_ACTIONS") == null) {
                    // Set syntax highlighting
                    String syntax = "text/" + Optional.of(file.getName())
                            .filter(f -> f.contains("."))
                            .map(f -> f.substring(file.getName().lastIndexOf(".") + 1)).get();
                    getGuiContentPane().setSyntax(syntax);
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
     * Search the textarea, get count of matches
     */
    public int search(String query) {
        return new SearchAction().searchTextArea(this, query);
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
     * @return If the action was run successfully
     */
    public boolean runAction(String name) {
        if (!registeredActions.containsKey(name)) {
            System.err.println("Action `" + name + "` is not registered");
            return false;
        }

        // Run action
        TextEditorAction foundAction = registeredActions.get(name);
        return foundAction.performAction(this);
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