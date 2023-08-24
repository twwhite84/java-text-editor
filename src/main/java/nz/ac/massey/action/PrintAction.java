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
        // Creates a background service to print document
        makePrintWorker(gui).execute();
        return true;
    }

    /**
     * Create a background service to start a print job
     *
     * @param gui GUI instance to print the document for
     * @return The background service
     */
    public SwingWorker<Void, Void> makePrintWorker(TextEditorGUI gui) {
        return new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() {
                try (PDDocument document = gui.getPdfFile()) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(document));
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