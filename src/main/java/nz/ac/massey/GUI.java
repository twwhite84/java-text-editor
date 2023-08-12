package nz.ac.massey;

import javax.swing.JFrame;

public class GUI {

  public static void createAndShowGUI() {
    JFrame frame = new JFrame("GridBagLayoutDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // pass frame's contentpane into this method to add the stuff
    new GUIContentPane().addComponentsToPane(frame.getContentPane());

    frame.setSize(1024, 768);
    // frame.pack();
    frame.setVisible(true);
  }

}
