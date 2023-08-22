package nz.ac.massey.gui;

import lombok.Getter;
import nz.ac.massey.SimpleKeybindAction;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Utilities;

import java.awt.*;

/**
 * Holds all content of the editor and displays it
 */
public class TextEditorContentPane extends Container {

    /**
     * Injected instance of main GUI
     */
    private final TextEditorGUI gui;

    /**
     * Search panel
     */
    @Getter
    private JPanel searchPanel;

    /**
     * Search panel elements
     */
    @Getter
    private JTextField txtSearchField;

    @Getter
    private JLabel lblMatches;

    @Getter
    private JButton btnSearchNext, btnSearchPrev;

    /**
     * The actual editable text area
     */
    @Getter
    private RSyntaxTextArea textArea;

    /**
     * Status bar object
     */
    private JPanel statusBar;

    /**
     * Labels for status bar
     */
    private JLabel lblPosition, lblWordWrap, lblSyntax;

    public TextEditorContentPane(TextEditorGUI gui) {
        this.gui = gui;
        init();
    }

    /**
     * Get instance of the content pane
     */
    public void init() {
        // search panel
        searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints searchPanelConstraints = new GridBagConstraints();

        searchPanelConstraints.gridx = 0;
        searchPanelConstraints.gridy = 0;
        searchPanelConstraints.anchor = GridBagConstraints.WEST;
        lblMatches = new JLabel("Search Matches: 0");
        searchPanel.add(lblMatches, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 0;
        searchPanelConstraints.gridy = 1;
        txtSearchField = new JTextField(20);
        searchPanel.add(txtSearchField, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 1;
        searchPanelConstraints.gridy = 0;
        btnSearchNext = new JButton("Next");
        searchPanel.add(btnSearchNext, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 1;
        searchPanelConstraints.gridy = 1;
        btnSearchPrev = new JButton("Prev");
        searchPanel.add(btnSearchPrev, searchPanelConstraints);

        // main text area
        textArea = new RSyntaxTextArea(4, 30);
        textArea.setFont(new Font(gui.getConfig().getDefaultFont(), Font.PLAIN, gui.getConfig().getDefaultFontSize()));
        textArea.setForeground(gui.getConfig().getFontColour());
        textArea.setBackground(gui.getConfig().getBackground());
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
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
        textArea.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    int offset = textArea.getCaretPosition();
                    int line = textArea.getLineOfOffset(textArea.getCaretPosition());
                    int column = offset - Utilities.getRowStart(textArea, offset);
                    lblPosition.setText("Line " + (line + 1) + ", Column " + (column + 1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // status bar panel
        statusBar = new JPanel(new GridBagLayout());
        GridBagConstraints statusBarConstraints;

        lblPosition = new JLabel("Line 1, Column 1");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 0;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        statusBar.add(lblPosition, statusBarConstraints);

        lblWordWrap = new JLabel("Word Wrap: OFF");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 1;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        statusBar.add(lblWordWrap, statusBarConstraints);

        lblSyntax = new JLabel("Syntax: None");
        statusBarConstraints = new GridBagConstraints();
        statusBarConstraints.gridx = 2;
        statusBarConstraints.ipady = 20;
        statusBarConstraints.ipadx = 20;
        statusBar.add(lblSyntax, statusBarConstraints);

        // adding elements to the content pane
        setLayout(new GridBagLayout());
        GridBagConstraints contentPaneConstraints;

        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 0;
        searchPanel.setVisible(false);
        add(searchPanel, contentPaneConstraints);

        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.fill = GridBagConstraints.BOTH;
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 1;
        contentPaneConstraints.weightx = 1;
        contentPaneConstraints.weighty = 1;
        add(scrollPane, contentPaneConstraints);

        contentPaneConstraints = new GridBagConstraints();
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 2;
        add(statusBar, contentPaneConstraints);

    }

    public void toggleWrapIndicator(Boolean wrapEnabled) {
        if (wrapEnabled)
            lblWordWrap.setText("Word Wrap: ON");
        else if (!wrapEnabled)
            lblWordWrap.setText("Word Wrap: OFF");
    }
}