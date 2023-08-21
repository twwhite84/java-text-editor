package nz.ac.massey.action;

import javax.swing.JOptionPane;

import nz.ac.massey.gui.TextEditorGUI;

public class ExitAction extends TextEditorAction {

    public ExitAction() {
        super("Exit");
    }

    @Override
    public void performAction(TextEditorGUI gui) {
        if (!gui.isSaved()) {
            if (gui.getOpenFile() == null && gui.getGuiContentPane().getTextArea().getText().length() == 0) {
                gui.getFrame().dispose();
            } else {
                int choice = JOptionPane.showOptionDialog(gui.getFrame(), "Save before exiting?", "Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (choice) {
                    case JOptionPane.YES_OPTION:
                        gui.save();
                        gui.getFrame().dispose();
                        break;

                    case JOptionPane.NO_OPTION:
                        gui.getFrame().dispose();
                        break;

                    default:
                        break;
                }
            }
        } else {
            gui.getFrame().dispose();
        }
    }
}