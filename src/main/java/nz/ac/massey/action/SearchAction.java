package nz.ac.massey.action;

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
    // gui.getGuiContentPane().toggleSearchFrame();
    gui.getGuiContentPane().toggleSearchPanel();

  }

}
