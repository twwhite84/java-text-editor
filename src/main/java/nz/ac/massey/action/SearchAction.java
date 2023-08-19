package nz.ac.massey.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nz.ac.massey.gui.TextEditorGUI;

public class SearchAction extends TextEditorAction {

  public SearchAction() {
    super("Search");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    /*
     * String query = JOptionPane.showInputDialog(null, "Search for...", "Search",
     * JOptionPane.PLAIN_MESSAGE, null, null,
     * null).toString();
     * 
     * // just find the first instance matching search. highlight it in editor.
     * // gui.getGuiContentPane().getTextArea().
     * String myText = gui.getGuiContentPane().getTextArea().getText();
     * System.out.println(myText.indexOf(query));
     * 
     * //need to highlight
     */

    // toggle the search panel
    gui.getGuiContentPane().toggleSearchPanel();

    // String query = gui.getGuiContentPane().getTxtSearchField().toString();

    // listeners for the search pane

    gui.getGuiContentPane()
        .getTxtSearchField()
        .addKeyListener(new KeyListener() {
          String query;

          @Override
          public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyChar());
          }

          @Override
          public void keyPressed(KeyEvent e) {
          }

          @Override
          public void keyReleased(KeyEvent e) {
            query = gui.getGuiContentPane().getTxtSearchField().getText();
            System.out.println(query);
          }

        });

  }

}
