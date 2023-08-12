package nz.ac.massey;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GUIContentPane {

  public Container getContentPane() {
    Container pane = new Container();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    JButton btnOK;

    btnOK = new JButton("OK");

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;

    // add the things
    pane.add(btnOK, c);

    // events go here
    btnOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(btnOK, "Message goes here", "Placeholder modal", 0);
      }
    });

    return pane;
  }
}