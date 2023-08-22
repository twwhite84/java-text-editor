package nz.ac.massey;

import nz.ac.massey.config.TextEditorConfigLoader;
import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import java.io.File;

/**
 * Entry point of application. Will start GUI.
 */
public class TextEditor {
    public static void main(String[] args) {
        try {
            // Set look of OS
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Load config
            File configFile = new File("config.yml");
            TextEditorConfigLoader loader = new TextEditorConfigLoader(configFile);

            // Run GUI window
            SwingUtilities.invokeLater(() -> new TextEditorGUI(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}