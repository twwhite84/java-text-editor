package nz.ac.massey;

import junit.framework.TestCase;
import nz.ac.massey.gui.TextEditorGUI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tests to see if a search on the testfile returns correct number of matches
 */
public class SearchTest extends TestCase {

  /**
   * Test instance of GUI to remove UI code
   */
  TextEditorGUI gui = new TextEditorGUI();

  @Test
  public void testSearch() throws IOException {
    File file = new File("src/test/resources/testTextFile.txt");
    gui.openFile(file);

    // using the testfile, a search for "basic" should result in 1
    assertEquals(gui.search("basic"), 1);

    // search for non-existent text should return 0
    assertEquals(gui.search("this should fail"), 0);

  }

}
