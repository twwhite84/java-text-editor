package nz.ac.massey;

import junit.framework.TestCase;
import nz.ac.massey.gui.TextEditorGUI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tests to make sure the editor writes content to the file correctly
 */
public class SaveTest extends TestCase {

    /**
     * Test instance of GUI to remove UI code
     */
    TextEditorGUI gui = new TextEditorGUI();

    @Test
    public void testSavingFile() throws IOException {
        // When first opening editor, in unsaved state
        assertFalse("Gui was in saved state when first opening", gui.isSaved());

        // Simulate selecting a location to save file
        File fileToSave = new File("src/test/resources/myfile.txt");
        gui.setOpenFile(fileToSave);

        // Save test content to file
        String content = "This is some text content";
        gui.save(content);

        // Assert saved state
        assertTrue("Gui was not marked as saved when it should be", gui.isSaved());

        // Assert content was saved to file
        String fileContent = new String(Files.readAllBytes(Paths.get(gui.getOpenFile().getPath())));
        assertEquals("File content was not equal to saved content", fileContent, content);
    }

}
