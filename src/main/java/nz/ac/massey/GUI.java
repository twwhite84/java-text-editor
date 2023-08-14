package nz.ac.massey;

import javax.swing.JFrame;

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