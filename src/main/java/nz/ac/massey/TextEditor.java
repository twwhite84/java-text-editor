package nz.ac.massey;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;

/**
 * Entry point of application. Will start GUI.
 */
public class TextEditor {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        javax.swing.SwingUtilities.invokeLater(TextEditorGUI::new);
    }
}