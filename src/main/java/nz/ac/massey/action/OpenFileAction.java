package nz.ac.massey.action;

import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Open a new file in the editor
 */
public class OpenFileAction extends TextEditorAction {

    public OpenFileAction() {
        super("Open");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);

        // Text file filter
        fileChooser.addChoosableFileFilter(new FileFilter() {
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
        });

        // OpenDocument Text filter
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "OpenDocument Text (*.odt)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".odt");
                }
            }
        });

        // Rich Text Format filter
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "Rich Text Format (*.rtf)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".rtf");
                }
            }
        });

        int result = fileChooser.showOpenDialog(gui.getFrame());

        // Open file if selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            gui.openFile(file);
            return true;
        }

        return false;
    }
}
