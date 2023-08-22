package nz.ac.massey;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;

/**
 * Entry point of application. Will start GUI.
 */
public class TextEditor {
    public static void main(String[] args) throws URISyntaxException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Load config
        TextEditorConfigLoader loader = new TextEditorConfigLoader(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.yml"));

        SwingUtilities.invokeLater(() -> new TextEditorGUI(loader.load()));
    }
}