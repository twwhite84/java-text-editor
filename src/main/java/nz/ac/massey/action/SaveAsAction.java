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
    public boolean performAction(TextEditorGUI gui) {
        // Prompt to choose file location
        JFileChooser fileChooser = new JFileChooser();

        // Add file filters for supported types
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

        FileFilter pdfFilter = new FileFilter() {
            public String getDescription() {
                return "PDF Document (*.pdf)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".pdf");
                }
            }
        };

        fileChooser.addChoosableFileFilter(textFilter);
        fileChooser.addChoosableFileFilter(pdfFilter);
        fileChooser.setFileFilter(textFilter);

        int result = fileChooser.showSaveDialog(gui.getFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Check extension
            if (fileChooser.getFileFilter().getDescription().endsWith("(*.txt)") && !file.getName().endsWith(".txt")) {
                // Make sure .txt if filter was text file
                file = new File(file.getPath() + ".txt");
            } else if (fileChooser.getFileFilter().getDescription().endsWith("(*.pdf)") && !file.getName().endsWith(".pdf")) {
                // Make sure .pdf if filter was PDF
                file = new File(file.getPath() + ".pdf");
            }

            gui.saveAs(file);
            return true;
        }

        return false;
    }
}
