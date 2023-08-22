package nz.ac.massey;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

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

}
