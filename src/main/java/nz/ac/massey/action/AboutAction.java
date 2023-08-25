package nz.ac.massey.action;

import javax.swing.JOptionPane;

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
        String appName = "Text Editor";
        String appAuthors = "Jamin Stratford and Tom White, 2023";
        String appDetails = "This app was created for Massey 159.251 Software Engineering";
        String message = appName + "\n" + appAuthors + "\n\n" + appDetails;
        JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

}
