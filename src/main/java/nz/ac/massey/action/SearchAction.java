package nz.ac.massey.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Toggles the search panel and handles panel events
 */
public class SearchAction extends TextEditorAction {

  /**
   * selectionIndex is for tracking which match is selected
   */
  private int selectionIndex;

  /**
   * holds cursor offsets from 0 for start of all matches in search
   */
  private ArrayList<Integer> offsets;

  public SearchAction() {
    super("Search");
  }

  @Override
  public void performAction(TextEditorGUI gui) {

    // toggle the search panel
    gui.getGuiContentPane().toggleSearchPanel();

    // listeners for the search pane
    JTextField searchField = gui.getGuiContentPane().getTxtSearchField();
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

    gui.getGuiContentPane().getBtnSearchNext().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectionIndex++;
        if (selectionIndex >= offsets.size())
          selectionIndex = 0;
        searchTextArea(gui, searchField.getText());
      }

    });

    gui.getGuiContentPane().getBtnSearchPrev().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectionIndex--;
        if (selectionIndex < 0)
          selectionIndex = offsets.size() - 1;
        searchTextArea(gui, searchField.getText());
      }

    });

  }

  private void searchTextArea(TextEditorGUI gui, String query) {
    JTextArea txtArea = gui.getGuiContentPane().getTextArea();
    txtArea.getHighlighter().removeAllHighlights();

    // no query entered
    if (query.length() == 0) {
      gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
      return;
    }

    HighlightPainter unselected = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
    HighlightPainter selected = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);

    int offset = 0;
    offsets = new ArrayList<Integer>();

    while (txtArea.getText().indexOf(query, offset) != -1) {
      offset = txtArea.getText().indexOf(query, offset);
      offsets.add(offset);
      offset += query.length();
    }

    // no matches found
    if (offsets.isEmpty()) {
      gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
      return;
    }

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

    gui.getGuiContentPane().getLblMatches().setText("Search Matches: " + offsets.size());
  }

}