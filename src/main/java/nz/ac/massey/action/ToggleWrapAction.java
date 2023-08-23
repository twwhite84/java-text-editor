package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

public class ToggleWrapAction extends TextEditorAction {

    public ToggleWrapAction() {
        super("Word Wrap");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        System.out.println("toggle word wrap");

        // menuItemWrap.addActionListener(e -> {
        // not actually implemented yet, this just toggles the indicator
        // gui.getGuiContentPane().toggleWrapIndicator(menuItemWrap.isSelected());
        // });

        return true;
    }

}
