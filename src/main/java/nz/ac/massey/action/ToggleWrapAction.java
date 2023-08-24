package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

/**
 * Action for toggling the word wrap and its indicator
 */
public class ToggleWrapAction extends TextEditorAction {

    public ToggleWrapAction() {
        super("Word Wrap");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        if (gui.getGuiContentPane().getTextArea().getLineWrap()) {
            // disable wrap
            gui.getGuiContentPane().getLblWordWrap().setText("Word Wrap: OFF");
            gui.getGuiContentPane().getTextArea().setLineWrap(false);
        } else {
            // enable wrap
            gui.getGuiContentPane().getLblWordWrap().setText("Word Wrap: ON");
            gui.getGuiContentPane().getTextArea().setLineWrap(true);
        }
        return true;
    }

}
