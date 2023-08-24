package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;
import nz.ac.massey.gui.TextEditorSearchMenu;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Toggles the search panel and handles searching text content
 */
public class SearchAction extends TextEditorAction {

    /**
     * Stores the index of the currently selected match from all matches
     */
    private int selectionIndex;

    /**
     * Holds cursor offsets from 0 for start of all matches in search
     */
    private final ArrayList<Integer> offsets = new ArrayList<>();

    /**
     * Default mouse listener backup, used to restore mouse behaviour. set once.
     */
    private final List<MouseListener> initialMouseListeners = new ArrayList<>();

    public SearchAction() {
        super("Search");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        // when first time performing action, backup the default mouse listeners
        if (initialMouseListeners.isEmpty()) {
            initialMouseListeners.addAll(Arrays.asList(gui.getGuiContentPane().getTextArea().getMouseListeners()));
        }

        // toggle the search panel
        toggleSearchPanel(gui);

        // removing any existing listeners when toggled
        JButton btnNext = gui.getGuiContentPane().getSearchMenu().getButtonSearchNext();;
        JButton btnPrev = gui.getGuiContentPane().getSearchMenu().getButtonSearchPrev();
        JTextField searchField = gui.getGuiContentPane().getSearchMenu().getSearchField();
        JTextArea txtArea = gui.getGuiContentPane().getTextArea();
        for (ActionListener al : btnNext.getActionListeners()) {
            btnNext.removeActionListener(al);
        }
        for (ActionListener al : btnPrev.getActionListeners()) {
            btnPrev.removeActionListener(al);
        }
        for (KeyListener kl : searchField.getKeyListeners()) {
            searchField.removeKeyListener(kl);
        }
        for (MouseListener ml : txtArea.getMouseListeners()) {
            txtArea.removeMouseListener(ml);
        }

        // if searchpanel is open then add listeners, otherwise restore mouse
        if (gui.getGuiContentPane().getSearchMenu().isVisible()) {
            searchField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    selectionIndex = 0;
                    searchTextArea(gui, searchField.getText());
                }
            });

            // Navigate to next match
            btnNext.addActionListener(e -> {
                selectionIndex++;
                if (selectionIndex >= offsets.size())
                    selectionIndex = 0;
                searchTextArea(gui, searchField.getText());
            });

            // Navigate to previous match
            btnPrev.addActionListener(e -> {
                selectionIndex--;
                if (selectionIndex < 0)
                    selectionIndex = offsets.size() - 1;
                searchTextArea(gui, searchField.getText());
            });

            // Auto close search panel when focusing on text area
            txtArea.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    performAction(gui);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        } else {
            // Restore non search mode listeners
            initialMouseListeners.forEach(txtArea::addMouseListener);
        }

        return true;
    }

    /**
     * Perform search on editor content for a string
     *
     * @param gui GUI instance to search
     * @param query The string used to match
     */
    public int searchTextArea(TextEditorGUI gui, String query) {
        JTextArea txtArea = null;

        // Remove highlights from previous search
        if (System.getenv("GITHUB_ACTIONS") == null) {
            txtArea = gui.getGuiContentPane().getTextArea();
            txtArea.getHighlighter().removeAllHighlights();
        }

        // no query entered
        if (query.length() == 0) {
            if (System.getenv("GITHUB_ACTIONS") == null) {
                gui.getGuiContentPane().getSearchMenu().getLabelMatches().setText("Search Matches: 0");
            }
            return 0;
        }

        // Highlight for matches
        HighlightPainter unselected = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
        HighlightPainter selected = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);

        int offset = 0;
        offsets.clear();

        // Perform search and find the offset of matches
        while (gui.getContent().indexOf(query, offset) != -1) {
            offset = gui.getContent().indexOf(query, offset);
            offsets.add(offset);
            offset += query.length();
        }

        // no matches found
        if (offsets.isEmpty()) {
            if (System.getenv("GITHUB_ACTIONS") == null) {
                gui.getGuiContentPane().getSearchMenu().getLabelMatches().setText("Search Matches: 0");
            }
            return 0;
        }

        if (System.getenv("GITHUB_ACTIONS") == null) {
            // highlight the unselected matches grey
            for (int i = 0; i < offsets.size(); i++) {
                if (i == selectionIndex)
                    continue;
                int current_offset = offsets.get(i);
                try {
                    txtArea.getHighlighter().addHighlight(current_offset, current_offset + query.length(), unselected);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            // highlight the currently selected match cyan
            try {
                txtArea.getHighlighter().addHighlight(offsets.get(selectionIndex), offsets.get(selectionIndex) + query.length(),
                        selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // move cursor to selected match and update count label
            txtArea.setCaretPosition(offsets.get(selectionIndex));
            gui.getGuiContentPane().getSearchMenu().getLabelMatches().setText("Search Matches: " + offsets.size());
        }

        return offsets.size();
    }

    /**
     * Sets visibility of panel and clears highlighting
     *
     * @param gui GUI to toggle search for
     */
    public void toggleSearchPanel(TextEditorGUI gui) {
        TextEditorSearchMenu searchPanel = gui.getGuiContentPane().getSearchMenu();
        JTextField searchTextField = searchPanel.getSearchField();
        JTextArea mainTextArea = gui.getGuiContentPane().getTextArea();

        if (searchPanel.isVisible()) {
            mainTextArea.getHighlighter().removeAllHighlights();
            searchPanel.setVisible(false);
        } else {
            searchPanel.setVisible(true);
            searchTextField.requestFocus();
        }
    }
}