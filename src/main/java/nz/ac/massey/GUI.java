package nz.ac.massey;

import javax.swing.JFrame;

public class GUI {

  public static void createAndShowGUI() {
    JFrame frame = new JFrame("Text Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add menu bar to the frame
    frame.setJMenuBar(new GUIMenuBar().createMenuBar());

    // add content pane to the frame
    frame.setContentPane(new GUIContentPane().getContentPane());

    frame.pack();
    frame.setVisible(true);
  }

}