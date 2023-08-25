package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Action for showing or hiding the status bar
 */
public class ToggleStatusAction extends TextEditorAction {

    public ToggleStatusAction() {
        super("Status Bar");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        // Toggle visibility of status bar
        gui.getGuiContentPane().getStatusBar().setVisible(!gui.getGuiContentPane().getStatusBar().isVisible());
        return true;
    }

}
