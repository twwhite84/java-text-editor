package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Displays the about modal
 */
public class AboutAction extends TextEditorAction {

    public AboutAction() {
        super("About");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        System.out.println("about screen");
        return true;
    }

}
