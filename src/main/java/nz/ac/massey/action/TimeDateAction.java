package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Insert the current time & date into the editor
 */
public class TimeDateAction extends TextEditorAction {

    public TimeDateAction() {
        super("Time and Date");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        // Obtain current time and date
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a dd/MM/yyyy");

        // Insert into editor
        gui.getGuiContentPane().getTextArea().insert(simpleDateFormat.format(date), gui.getGuiContentPane().getTextArea().getCaretPosition());
        return true;
    }
}
