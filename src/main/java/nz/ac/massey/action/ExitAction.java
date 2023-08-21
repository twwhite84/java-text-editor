package nz.ac.massey.action;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import nz.ac.massey.gui.TextEditorGUI;

public class ExitAction extends TextEditorAction {

    public ExitAction() {
        super("Exit");
    }

    @Override
    public void performAction(TextEditorGUI gui) {
        if (gui.getOpenFile() == null && gui.getGuiContentPane().getTextArea().getText().length() == 0) {
            gui.getFrame().dispose();
        } else {
            // do you wish to save before exiting?
            // save, dont save, cancel
            int choice = JOptionPane.showOptionDialog(gui.getFrame(), "Save before exiting?", "Exit",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            switch (choice) {
                case JOptionPane.YES_OPTION:
                    System.out.println("user chose yes");
                    break;

                case JOptionPane.NO_OPTION:
                    System.out.println("user chose no");
                    break;

                default:
                    System.out.println("cancel");
                    break;
            }
        }
    }

}