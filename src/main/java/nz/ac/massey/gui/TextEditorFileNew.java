package nz.ac.massey.gui;

/**
 * Class for opening a new window
 */
public class TextEditorFileNew {
  public void menuFileNew() {
    javax.swing.SwingUtilities.invokeLater(TextEditorGUI::new);
  }
}