package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

public class PrintAction extends TextEditorAction {

    public PrintAction() {
        super("Print");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        System.out.println("print");
        return true;
    }

}
