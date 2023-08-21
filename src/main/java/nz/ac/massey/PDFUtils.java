package nz.ac.massey;

import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

/**
 * Util methods to help with PDF conversion
 */
public class PDFUtils {

    /**
     * Remove all illegal characters for PDF encoding
     *
     * @param string Raw string
     * @return Sanitised string
     */
    public static String santiseString(String string) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (WinAnsiEncoding.INSTANCE.contains(string.charAt(i))) {
                b.append(string.charAt(i));
            }
        }
        return b.toString();
    }

}
