package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

public class SearchAction extends TextEditorAction {

  public SearchAction() {
    super("Search");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    System.out.println("SEARCH ACTION CALLED");
  }

}
