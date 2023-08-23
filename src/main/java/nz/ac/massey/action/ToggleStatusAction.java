package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

public class ToggleStatusAction extends TextEditorAction {

    public ToggleStatusAction() {
        super("Status Bar");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {

        if (gui.getGuiContentPane().getStatusBar().isVisible()) {
            gui.getGuiContentPane().getStatusBar().setVisible(false);
        } else {
            gui.getGuiContentPane().getStatusBar().setVisible(true);
        }

        return true;
    }

}
