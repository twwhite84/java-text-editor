package nz.ac.massey.action;

import java.awt.print.PrinterJob;
import javax.swing.SwingWorker;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import nz.ac.massey.gui.TextEditorGUI;

/**
 * Converts editor content to PDF and sends to selected print service
 */
public class PrintAction extends TextEditorAction {

    public PrintAction() {
        super("Print");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        makePrintWorker(gui).execute();
        return true;
    }

    public SwingWorker<Void, Void> makePrintWorker(TextEditorGUI gui) {
        return new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                try (PDDocument pddoc = gui.getPdfFile()) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(pddoc));
                    if (job.printDialog()) {
                        job.print();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
            }
        };
    }
}