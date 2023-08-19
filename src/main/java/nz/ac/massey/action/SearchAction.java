package nz.ac.massey.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

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
    if (query.length() == 0) {
      gui.getGuiContentPane().getLblMatches().setText("Search Matches: 0");
      return;
    }

    String textArea = gui.getGuiContentPane().getTextArea().getText();

    // count up how many matches there are in the textarea
    int offset = 0, count = 0;

    while (textArea.indexOf(query, offset) != -1) {
      offset = textArea.indexOf(query, offset);
      offset += query.length();
      count++;
    }

    System.out.println(count);
    gui.getGuiContentPane().getLblMatches().setText("Search Matches: " + count);

  }

}