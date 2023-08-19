package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Saves the openFile from the {@link TextEditorGUI} to that file location
 */
public class SaveAction extends TextEditorAction {

    public SaveAction() {
        super("Save");
    }

    @Override
    public void performAction(TextEditorGUI gui) {
        gui.save();
    }
}
