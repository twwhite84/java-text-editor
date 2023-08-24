package nz.ac.massey.gui;

import lombok.Getter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

/**
 * Holds all content of the editor and displays it
 */
public class TextEditorContentPane extends Container {

    /**
     * Injected instance of main GUI
     */
    private final TextEditorGUI gui;

    @Getter
    private TextEditorSearchMenu searchMenu;

    /**
     * The actual editable text area
     */
    @Getter
    private RSyntaxTextArea textArea;

    /**
     * Status bar object
     */
    @Getter
    private TextEditorStatusBar statusBar;

    public TextEditorContentPane(TextEditorGUI gui) {
        this.gui = gui;
        init();
    }

    /**
     * Get instance of the content pane
     */
    public void init() {
        // search panel
        searchMenu = new TextEditorSearchMenu();

        // main text area
        textArea = new RSyntaxTextArea(4, 30);
        textArea.setFont(new Font(gui.getConfig().getDefaultFont(), Font.PLAIN, gui.getConfig().getDefaultFontSize()));
        textArea.setForeground(gui.getConfig().getFontColour());
        textArea.setBackground(gui.getConfig().getBackground());
        textArea.setHighlightCurrentLine(false);

        // Default code formatting is none
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);

        // When updating text, set file to unsaved state
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                gui.setContent(textArea.getText(), false);
                gui.setSaved(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                gui.setContent(textArea.getText(), false);
                gui.setSaved(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                gui.setContent(textArea.getText(), false);
                gui.setSaved(false);
            }
        });

        // update position displayed on status bar when cursor moves
        textArea.addCaretListener(e -> {
            try {
                int line = textArea.getLineOfOffset(textArea.getCaretPosition());
                int column = textArea.getCaretOffsetFromLineStart();
                this.statusBar.getLabelPosition().setText("Line " + (line + 1) + ", Column " + (column + 1));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add text area to scrollable area
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // status bar panel
        statusBar = new TextEditorStatusBar();

        // Adding elements to the content pane
        setLayout(new GridBagLayout());
        GridBagConstraints contentPaneConstraints;

        // Add toggleable search menu
        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 0;
        searchMenu.setVisible(false);
        add(searchMenu, contentPaneConstraints);

        // Add main text area
        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.fill = GridBagConstraints.BOTH;
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 1;
        contentPaneConstraints.weightx = 1;
        contentPaneConstraints.weighty = 1;
        add(scrollPane, contentPaneConstraints);

        // Add status bar
        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 2;
        add(statusBar, contentPaneConstraints);
    }

    /**
     * Sets syntax highlighting of editor
     *
     * @param syntax Syntax to use see {@link SyntaxConstants}
     */
    public void setSyntax(String syntax) {
        this.textArea.setSyntaxEditingStyle(syntax);
        this.statusBar.getLabelSyntax().setText("Syntax: " + syntax);
    }
}