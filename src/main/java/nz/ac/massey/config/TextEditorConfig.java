package nz.ac.massey.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

/**
 * Model of the config for the text editor
 */
@RequiredArgsConstructor
@Getter
public class TextEditorConfig {

    /**
     * Default font size of the text editor
     */
    private final int defaultFontSize;

    /**
     * Default font used to display text
     */
    private final String defaultFont;

    /**
     * Background of text area
     */
    @Getter
    private final Color background;

    /**
     * Default colour of font
     */
    @Getter
    private final Color fontColour;

}
