package nz.ac.massey;

import javax.swing.JFrame;

// i set this class up to act like a central hub
// injects itself through the menubar and contentpane class constructors
// so they can access methods on each other via protected variables below
// not sure if best approach?
public class GUI {
  protected GUIMenuBar guiMenuBar;
  protected GUIContentPane guiContentPane;

  public GUI() {
    guiMenuBar = new GUIMenuBar(this);
    guiContentPane = new GUIContentPane(this);
  }

  public void createAndShowGUI() {
    JFrame frame = new JFrame("Text Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(guiMenuBar.getMenuBar());
    frame.setContentPane(guiContentPane.getContentPane());
    frame.pack();
    frame.setVisible(true);
  }

}