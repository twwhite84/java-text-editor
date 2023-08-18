package nz.ac.massey.action;

import javax.swing.JOptionPane;

import nz.ac.massey.gui.TextEditorGUI;

public class SearchAction extends TextEditorAction {

  public SearchAction() {
    super("Search");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    String query = JOptionPane.showInputDialog(null, "Search for...", "Search", JOptionPane.PLAIN_MESSAGE, null, null,
        null).toString();
    System.out.println(query.toString());
  }

}
