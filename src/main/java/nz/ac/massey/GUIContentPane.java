package nz.ac.massey;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;

public class GUIContentPane {
  public void addComponentsToPane(Container pane) {
    // define the stuff that'll go on the passed pane
    JButton button;
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    button = new JButton("Button 1");

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;

    // add stuff to the passed pane
    pane.add(button, c);
  }
}