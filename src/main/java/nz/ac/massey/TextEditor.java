package nz.ac.massey;

/**
 * Entry point of application. Will start GUI.
 */
public class TextEditor {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new GUI().createAndShowGUI();
      }
    });
  }
}