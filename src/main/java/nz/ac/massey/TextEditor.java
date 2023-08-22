package nz.ac.massey;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import java.io.File;

/**
 * Entry point of application. Will start GUI.
 */
public class TextEditor {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Load config
        TextEditorConfigLoader loader = new TextEditorConfigLoader(new File("config.yml"));

        SwingUtilities.invokeLater(() -> new TextEditorGUI(loader.load()));
    }
}