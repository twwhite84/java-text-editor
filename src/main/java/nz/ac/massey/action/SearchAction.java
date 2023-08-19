package nz.ac.massey.action;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Toggles the search panel and handles panel events
 */
public class SearchAction extends TextEditorAction {

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
        searchTextArea(gui, searchField.getText());
      }

    });

  }

  private void searchTextArea(TextEditorGUI gui, String query) {
    JTextArea txtArea = gui.getGuiContentPane().getTextArea();
    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);

    txtArea.getHighlighter().removeAllHighlights();

    if (query.length() == 0) {
      gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
      return;
    }

    String textAreaText = gui.getGuiContentPane().getTextArea().getText();

    // count up how many matches there are in the textarea
    int offset = 0, count = 0;

    while (textAreaText.indexOf(query, offset) != -1) {
      try {
        offset = textAreaText.indexOf(query, offset);
        txtArea.getHighlighter().addHighlight(offset, offset + query.length(), painter);
        offset += query.length();
        count++;
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    gui.getGuiContentPane().getLblMatches().setText("Search Matches: " + count);
  }

}