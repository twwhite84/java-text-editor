package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;

/**
 * Create a new window with a new file object
 */
public class NewFileAction extends TextEditorAction {

    public NewFileAction() {
        super("New");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        SwingUtilities.invokeLater(TextEditorGUI::new);
        return true;
    }
}
