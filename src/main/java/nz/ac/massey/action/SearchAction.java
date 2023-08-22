package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Toggles the search panel and handles panel events
 */
public class SearchAction extends TextEditorAction {

    /**
     * stores the index of the currently selected match from all matches
     */
    private int selectionIndex;

    /**
     * holds cursor offsets from 0 for start of all matches in search
     */
    private ArrayList<Integer> offsets = new ArrayList<>();

    /**
     * default mouse listener backup, used to restore mouse behaviour. set once.
     */
    private MouseListener initialMouseListener;

    public SearchAction() {
        super("Search");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        // when first time performing action, backup the default mouse listener
        if (initialMouseListener == null) {
            for (MouseListener ml : gui.getGuiContentPane().getTextArea().getMouseListeners()) {
                initialMouseListener = ml;
            }
        }

        // toggle the search panel
        toggleSearchPanel(gui);

        // removing any existing listeners when toggled
        JButton btnNext = gui.getGuiContentPane().getBtnSearchNext();
        JButton btnPrev = gui.getGuiContentPane().getBtnSearchPrev();
        JTextField searchField = gui.getGuiContentPane().getTxtSearchField();
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
        if (gui.getGuiContentPane().getSearchPanel().isVisible()) {
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

            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectionIndex++;
                    if (selectionIndex >= offsets.size())
                        selectionIndex = 0;
                    searchTextArea(gui, searchField.getText());
                }
            });

            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectionIndex--;
                    if (selectionIndex < 0)
                        selectionIndex = offsets.size() - 1;
                    searchTextArea(gui, searchField.getText());
                }
            });

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
            txtArea.addMouseListener(initialMouseListener);
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

        if (System.getenv("GITHUB_ACTIONS") == null) {
            txtArea = gui.getGuiContentPane().getTextArea();
            txtArea.getHighlighter().removeAllHighlights();
        }

        // no query entered
        if (query.length() == 0) {
            if (System.getenv("GITHUB_ACTIONS") == null) {
                gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
            }
            return 0;
        }

        HighlightPainter unselected = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
        HighlightPainter selected = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);

        int offset = 0;
        offsets.clear();

        // Perform search
        while (gui.getContent().indexOf(query, offset) != -1) {
            offset = gui.getContent().indexOf(query, offset);
            offsets.add(offset);
            offset += query.length();
        }

        // no matches found
        if (offsets.isEmpty()) {
            if (System.getenv("GITHUB_ACTIONS") == null) {
                gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
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
            gui.getGuiContentPane().getLblMatches().setText("Search Matches: " + offsets.size());
        }

        return offsets.size();
    }

    /**
     * sets visibility of panel and clears highlighting
     *
     * @param gui
     */
    public void toggleSearchPanel(TextEditorGUI gui) {
        JPanel searchPanel = gui.getGuiContentPane().getSearchPanel();
        JTextField searchTextField = gui.getGuiContentPane().getTxtSearchField();
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