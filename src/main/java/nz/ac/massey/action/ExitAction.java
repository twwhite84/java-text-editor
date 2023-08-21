package nz.ac.massey.action;

import javax.swing.JTextArea;

import nz.ac.massey.gui.TextEditorGUI;

public class ExitAction extends TextEditorAction {

  public ExitAction() {
    super("Exit");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    if (gui.getOpenFile() == null && gui.getGuiContentPane().getTextArea().getText().length() > 0) {
      // do you wish to save before exiting?
      // save, dont save, cancel
    } else {
      System.out.println("should ask to save again and quit");
    }
  }

}
