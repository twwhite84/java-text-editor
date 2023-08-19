package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Prompts to choose a file location to save the contents to
 */
public class SaveAsAction extends TextEditorAction {

    public SaveAsAction() {
        super("Save As");
    }

    @Override
    public void performAction(TextEditorGUI gui) {
        // Prompt to choose file location
        JFileChooser fileChooser = new JFileChooser();

        // Add file filters for supported types
        // @todo Add support for code based files and PDF issue #14
        FileFilter textFilter = new FileFilter() {
            public String getDescription() {
                return "Text Files (*.txt)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".txt");
                }
            }
        };

        fileChooser.addChoosableFileFilter(textFilter);
        fileChooser.setFileFilter(textFilter);

        int result = fileChooser.showSaveDialog(gui.getFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Check extension
            if (fileChooser.getFileFilter().getDescription().endsWith("(*.txt)")) {
                // Make sure .txt
                if (!file.getName().endsWith(".txt"))
                    file = new File(file.getPath() + ".txt");
            } else if (fileChooser.getFileFilter().getDescription().endsWith("(*.pdf)")) {
                // Make sure .pdf
                if (!file.getName().endsWith(".pdf"))
                    file = new File(file.getPath() + ".pdf");
            }

            gui.saveAs(file);
        }
    }
}
