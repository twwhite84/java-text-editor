package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Create a new window with a new file object
 */
public class NewFileAction extends TextEditorAction {

    public NewFileAction() {
        super("New");
    }

    @Override
    public void performAction(TextEditorGUI gui) {
        javax.swing.SwingUtilities.invokeLater(TextEditorGUI::new);
    }
}
