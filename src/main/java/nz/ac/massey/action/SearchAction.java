package nz.ac.massey.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
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

    // adding listeners for the search pane
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
        System.out.println("next");
        selectionIndex++;
        if (selectionIndex >= offsets.size())
          selectionIndex = 0;
        searchTextArea(gui, searchField.getText());
      }
    });

    btnPrev.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("prev");
        selectionIndex--;
        if (selectionIndex < 0)
          selectionIndex = offsets.size() - 1;
        searchTextArea(gui, searchField.getText());
      }
    });

    txtArea.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        gui.getGuiContentPane().toggleSearchPanel();
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

    // move cursor to selected match and update count label
    txtArea.setCaretPosition(offsets.get(selectionIndex));
    gui.getGuiContentPane().getLblMatches().setText("Search Matches: " + offsets.size());
  }
}