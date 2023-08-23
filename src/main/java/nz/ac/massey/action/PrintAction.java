package nz.ac.massey.action;

import java.awt.print.Printable;

import javax.swing.JTextArea;

import org.apache.pdfbox.pdmodel.PDDocument;

import nz.ac.massey.gui.TextEditorGUI;

public class PrintAction extends TextEditorAction {

    public PrintAction() {
        super("Print");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        JTextArea textArea = gui.getGuiContentPane().getTextArea();
        try {
            textArea.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}