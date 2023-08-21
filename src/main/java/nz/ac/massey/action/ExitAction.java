package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

public class ExitAction extends TextEditorAction {

  public ExitAction() {
    super("Exit");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    System.out.println("exit function");
  }

}
